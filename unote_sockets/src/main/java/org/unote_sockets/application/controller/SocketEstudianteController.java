package org.unote_sockets.application.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.unote_sockets.models.generics.EstadoTareaGeneric;
import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;
import org.unote_sockets.models.vistasmaterializadas.VistaTarea;

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
@ServerEndpoint("/vistaEstudiante/{estudianteID}")
public class SocketEstudianteController {

    private static Map<String, Map<String, Session>> sessions;
    private final Gson gson = new Gson();

    public SocketEstudianteController() {
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
    public void onOpen(Session session, @PathParam("estudianteID") String estudianteID) {

        log.info(String.format("Conectado desde el panel del estudiante %s", estudianteID));
        Map<String, Session> sessionMap = sessions.getOrDefault(estudianteID, new HashMap<>());

        sessionMap.put(session.getId(), session);
        sessions.put(estudianteID, sessionMap);
    }

    @OnClose
    public void onClose(Session session, @PathParam("estudianteID") String estudianteID) {
        sessions
                .get(estudianteID)
                .remove(session.getId());

        log.info(String.format("Estudiante %s desconectado de su panel", estudianteID));
    }

    @OnError
    public void onError(Session session, @PathParam("estudianteID") String estudianteID, Throwable throwable) {
        sessions
                .get(estudianteID)
                .remove(session.getId());

        log.error(throwable.getMessage());

    }

    public void emitirCreacionTarea(String estudianteID, EstadoTareaGeneric estadoTareaGeneric) {
        if (Objects.nonNull(estudianteID) && sessions.containsKey(estudianteID)) {
            broadcastJSON(gson.toJson(estadoTareaGeneric), estudianteID);
            log.info("Creación de tarea emitida desde el websocket");
        }
    }

    public void emitirCalificacionTarea(String estudianteID, VistaEstudiante vistaEstudiante) {
        if (Objects.nonNull(estudianteID) && sessions.containsKey(estudianteID)) {
            broadcastJSON(gson.toJson(vistaEstudiante), estudianteID);
            log.info("Calificación de tareaemitida desde el websocket");

        }
    }


}
