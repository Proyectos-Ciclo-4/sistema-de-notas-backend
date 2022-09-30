package org.unote_sockets.application.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/vistaEstudiante/{profesorID}")
public class SocketProfesorController {
    private static Map<String, Map<String, Session>> sessions;
    private final Gson gson = new Gson();

    public SocketProfesorController() {
        if (Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }

    private static void broadcastJSON(String JSONMessage, String targetCorrelationId) {
        sessions.get(targetCorrelationId)
                .values().forEach(session -> {
                    try {
                        session.getAsyncRemote().sendText(JSONMessage);
                    } catch (RuntimeException runtimeException) {
                        log.error(runtimeException.getMessage());
                    }
                });
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("profesorID") String profesorID) {

        log.info(String.format("Conectado desde el panel del profesor %s", profesorID));
        Map<String, Session> sessionMap = sessions.getOrDefault(profesorID, new HashMap<>());

        sessionMap.put(session.getId(), session);
        sessions.put(profesorID, sessionMap);
    }

    @OnClose
    public void onClose(Session session, @PathParam("profesorID") String profesorID) {
        sessions
                .get(profesorID)
                .remove(session.getId());

        log.info(String.format("Profesor %s desconectado de su panel", profesorID));
    }

    @OnError
    public void onError(Session session, @PathParam("profesorID") String profesorID, Throwable throwable) {
        sessions
                .get(profesorID)
                .remove(session.getId());

        log.error(throwable.getMessage());
    }

    public void emitirNuevaInscripcion(String profesorID, VistaEstudiante vistaEstudiante) {
        if (Objects.nonNull(profesorID) && sessions.containsKey(profesorID)) {
            broadcastJSON(gson.toJson(vistaEstudiante), profesorID);
            log.info("Nueva inscripción emitida desde el web socket");
        }
    }

    public void emtirNuevaEntrega(String profesorID, VistaEstudiante vistaEstudiante) {
        if (Objects.nonNull(profesorID) && sessions.containsKey(profesorID)) {
            broadcastJSON(gson.toJson(vistaEstudiante), profesorID);
            log.info("Nueva entrega emitida desde el web socket");

        }
    }

}
