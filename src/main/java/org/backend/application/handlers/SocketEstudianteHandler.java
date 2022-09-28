package org.backend.application.handlers;

import lombok.extern.slf4j.Slf4j;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/vistaEstudiante/{estudianteID}")
public class SocketEstudianteHandler {

    private static Map<String, Map<String, Session>> sessions;


    public SocketEstudianteHandler() {
        if(Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }

    //private static void emitirVistaEstudiante(VistaEstudiante vistaEstudiante, )
}
