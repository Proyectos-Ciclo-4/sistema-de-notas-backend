package org.unote_sockets.application.bus;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.unote_sockets.application.bus.notificationmodels.NotificationNuevaTarea;
import org.unote_sockets.application.controller.SocketEstudianteController;
import org.unote_sockets.application.controller.SocketProfesorController;
import org.unote_sockets.models.vistasmaterializadas.VistaEstudiante;
import org.unote_sockets.models.vistasmaterializadas.VistaTarea;

@Slf4j
@Component
public class RabbitMQConsumer {

    private final Gson gson = new Gson();

    SocketEstudianteController socketEstudianteController;

    SocketProfesorController socketProfesorController;

    public RabbitMQConsumer(SocketEstudianteController socketEstudianteController, SocketProfesorController socketProfesorController) {
        this.socketEstudianteController = socketEstudianteController;
        this.socketProfesorController = socketProfesorController;
    }

    // Queues
    public static final String PUBLICAR_TAREA_NUEVA_QUEUE = "estudiante.tarea_nueva";
    public static final String PUBLICAR_CALIFICACION_QUEUE = "estudiante.calificacion";
    public static final String PUBLICAR_NUEVO_INSCRITO_QUEUE = "profesor.inscrito_nuevo";
    public static final String PUBLICAR_ENTREGA_TAREA_QUEUE = "profesor.entrega_tarea";

    @RabbitListener(queues = PUBLICAR_TAREA_NUEVA_QUEUE)
    public void escucharTareaNueva(String tareaNuevaJSON) {
        NotificationNuevaTarea notificationNuevaTarea = gson.fromJson(tareaNuevaJSON, NotificationNuevaTarea.class);
        log.info(String.format("Creación de tarea %s recibida en queue", notificationNuevaTarea.getEstadoTareaGeneric().getTareaID()));
        socketEstudianteController.emitirCreacionTarea(
                notificationNuevaTarea.getEstudianteID(),
                notificationNuevaTarea.getEstadoTareaGeneric()
        );

    }

    @RabbitListener(queues = PUBLICAR_CALIFICACION_QUEUE)
    public void escucharCalificacion(String calificacionJSON) {
        VistaEstudiante vistaEstudiante = gson.fromJson(calificacionJSON, VistaEstudiante.class);
        log.info(String.format("Calificación a estudiante %s recibida en queue", vistaEstudiante.get_id()));
        // TODO: reenviar a socket
    }

    @RabbitListener(queues = PUBLICAR_NUEVO_INSCRITO_QUEUE)
    public void escucharInscritoNuevo(String inscritoJSON) {
        VistaEstudiante vistaEstudiante = gson.fromJson(inscritoJSON, VistaEstudiante.class);
        log.info(String.format("Nuevo Inscrito %s recibido en queue", vistaEstudiante.get_id()));
        // TODO: reenviar a socket
    }

    @RabbitListener(queues = PUBLICAR_ENTREGA_TAREA_QUEUE)
    public void escucharEntregaTarea(String entregaJSON) {
        VistaEstudiante vistaEstudiante = gson.fromJson(entregaJSON, VistaEstudiante.class);
        log.info(String.format("Entrega de studiante %s recibida en queue", vistaEstudiante.get_id()));
        // TODO: reenviar a socket

    }

}
