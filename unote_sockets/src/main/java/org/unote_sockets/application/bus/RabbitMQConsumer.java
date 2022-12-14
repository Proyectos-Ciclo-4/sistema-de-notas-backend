package org.unote_sockets.application.bus;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.unote_sockets.application.bus.notificationmodels.NotificationNuevaInscripcion;
import org.unote_sockets.application.bus.notificationmodels.NotificationNuevaTarea;
import org.unote_sockets.application.bus.notificationmodels.NotificationTareaActualizada;
import org.unote_sockets.application.bus.notificationmodels.NotificationTareaEntregada;
import org.unote_sockets.application.controller.SocketEstudianteController;
import org.unote_sockets.application.controller.SocketProfesorController;

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
        notificationNuevaTarea.getEstadoTareaGeneric().setTipo(PUBLICAR_TAREA_NUEVA_QUEUE);
        log.info(String.format(
                "Creación de tarea %s recibida en queue",
                notificationNuevaTarea.getEstadoTareaGeneric().getTareaID()
        ));

        socketEstudianteController.emitirCreacionTarea(
                notificationNuevaTarea.getEstudianteID(),
                notificationNuevaTarea
        );

    }

    @RabbitListener(queues = PUBLICAR_CALIFICACION_QUEUE)
    public void escucharCalificacion(String tareaActualizadaJSON) {
        NotificationTareaActualizada notificationTareaActualizada = gson.fromJson(tareaActualizadaJSON, NotificationTareaActualizada.class);
    notificationTareaActualizada.getEstadoTareaGeneric().setTipo(PUBLICAR_CALIFICACION_QUEUE);
        log.info(String.format(
                "Calificación de tarea %s a estudiante %s recibida en queue.",
                notificationTareaActualizada.getEstadoTareaGeneric().getTareaID(),
                notificationTareaActualizada.getEstudianteID()
        ));

        socketEstudianteController.emitirCalificacionTarea(
                notificationTareaActualizada.getEstudianteID(),
                notificationTareaActualizada
        );
    }

    @RabbitListener(queues = PUBLICAR_NUEVO_INSCRITO_QUEUE)
    public void escucharInscritoNuevo(String inscritoJSON) {
        NotificationNuevaInscripcion notificationNuevaInscripcion = gson.fromJson(inscritoJSON, NotificationNuevaInscripcion.class);
        notificationNuevaInscripcion.getVistaEstudiante().setTipo(PUBLICAR_NUEVO_INSCRITO_QUEUE);
        log.info(String.format(
                "Inscripcion de %s en curso de profesor %s recibida en queue",
                notificationNuevaInscripcion.getVistaEstudiante().get_id(),
                notificationNuevaInscripcion.getProfesorID()
        ));

        socketProfesorController.emitirNuevaInscripcion(
                notificationNuevaInscripcion.getProfesorID(),
                notificationNuevaInscripcion
        );
    }

    @RabbitListener(queues = PUBLICAR_ENTREGA_TAREA_QUEUE)
    public void escucharEntregaTarea(String entregaJSON) {
        NotificationTareaEntregada notificationTareaEntregada = gson.fromJson(entregaJSON, NotificationTareaEntregada.class);
        notificationTareaEntregada.getEstadoTareaGeneric().setTipo(PUBLICAR_ENTREGA_TAREA_QUEUE);
        log.info(String.format(
                "Tarea %s recibida en queue",
                notificationTareaEntregada.getEstadoTareaGeneric().getTareaID()));

        socketProfesorController.emtirNuevaEntrega(
                notificationTareaEntregada.getProfesorID(),
                notificationTareaEntregada
        );
    }

}
