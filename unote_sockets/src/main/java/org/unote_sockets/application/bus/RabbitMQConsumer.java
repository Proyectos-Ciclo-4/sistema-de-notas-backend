package org.unote_sockets.application.bus;

import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private final Gson gson = new Gson();

    //TODO: inyectar socket handler
    // Constructor para inyectar socket handler

    // Queues
    public static final String PUBLICAR_TAREA_NUEVA_QUEUE = "estudiante.tarea_nueva";
    public static final String PUBLICAR_CALIFICACION_QUEUE = "estudiante.calificacion";
    public static final String PUBLICAR_NUEVO_INSCRITO_QUEUE = "profesor.inscrito_nuevo";
    public static final String PUBLICAR_ENTREGA_TAREA_QUEUE = "profesor.entrega_tarea";

    @RabbitListener(queues = PUBLICAR_TAREA_NUEVA_QUEUE)
    public void escucharTareaNueva(String tareaNuevaJSON) {
        System.out.println("lol");

    }


}
