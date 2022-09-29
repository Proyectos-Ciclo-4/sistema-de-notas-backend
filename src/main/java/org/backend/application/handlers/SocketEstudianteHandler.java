package org.backend.application.handlers;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.springframework.stereotype.Component;

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
import java.util.logging.Level;

@Slf4j
@Component
@ServerEndpoint("/vistaEstudiante/{estudianteID}")
public class SocketEstudianteHandler {

    private static Map<String, Map<String, Session>> sessions;
    private final Gson gson = new Gson();

    public SocketEstudianteHandler() {
        if(Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("estudianteID") String correlationID) {
        log.info(String.format("Conexión establecida desde %s", correlationID));
        Map<String, Session> sessionMap = sessions.getOrDefault(correlationID, new HashMap<>());

        sessionMap.put(session.getId(), session);
        sessions.put(correlationID, sessionMap);
    }

    @OnClose
    public void onClose(Session session, @PathParam("estudianteID") String correlationID) {
        sessions
                .get(correlationID)
                .remove(session.getId());

        log.info(String.format("Desconectado desde %s", correlationID));
    }

    @OnError
    public void onError(Session session, @PathParam("estudianteID") String correlationID, Throwable throwable) {
        sessions
                .get(correlationID)
                .remove(session.getId());

        log.error(throwable.getMessage());

    }

    private static void broadcastJSON(String JSONMessage, String targetCorrelationID) {
        log.info(String.format("Emitido desde %s", targetCorrelationID));

        sessions.get(targetCorrelationID)
                .values().forEach(session -> {
                    try {
                        session.getAsyncRemote().sendText(JSONMessage);
                    } catch (RuntimeException runtimeException) {
                        log.error(runtimeException.getMessage());
                    }
                });
    }

    public void emitirCalificacionActualizada(String correlationID, Integer calificacion) {
        if (Objects.nonNull(correlationID) && sessions.containsKey(correlationID)) {
            broadcastJSON(gson.toJson(calificacion), correlationID);
        }
    }

}
