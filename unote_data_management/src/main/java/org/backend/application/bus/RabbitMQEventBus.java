package org.backend.application.bus;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.backend.application.bus.notificationmodels.NotificationNuevaInscripcion;
import org.backend.application.bus.notificationmodels.NotificationNuevaTarea;
import org.backend.application.bus.notificationmodels.NotificationTareaActualizada;
import org.backend.application.bus.notificationmodels.NotificationTareaEntregada;
import org.backend.application.config.RabbitConfig;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RabbitMQEventBus {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public RabbitMQEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private void convertAndSend(String routingKey, byte[] objetivo) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                routingKey,
                objetivo
        );
    }

    public void publicarTareaNueva(String estudianteID, EstadoTareaGeneric estadoTareaGeneric){
        log.info(String.format("Tarea %s emitida a queue VISTA ESTUDIANTE", estadoTareaGeneric.getTareaID()));

        convertAndSend(
                RabbitConfig.PUBLICAR_TAREA_NUEVA_QUEUE,
                gson.toJson(
                        new NotificationNuevaTarea(
                                estudianteID,
                                estadoTareaGeneric
                        )
                ).getBytes());
    }

    public void publicarCalificacion(NotificationTareaActualizada notificationTareaActualizada) {
        log.info(String.format(
                "Calificacion de tarea %s de estudiante %s emitida a queue VISTA ESTUDIANTE",
                notificationTareaActualizada.getEstudianteID(),
                notificationTareaActualizada.getEstadoTareaGeneric().getTareaID()
                ));

        convertAndSend(
                RabbitConfig.PUBLICAR_CALIFICACION_QUEUE,
                gson.toJson(notificationTareaActualizada).getBytes());
    }

    public void publicarNuevoInscrito(NotificationNuevaInscripcion notificationNuevaInscripcion) {
        log.info(String.format(
                "Inscripción de estudiante %s a curso de profesor %s emitida a queue VISTA PROFESOR",
                notificationNuevaInscripcion.getVistaEstudiante().get_id(),
                notificationNuevaInscripcion.getProfesorID()
                ));

        convertAndSend(
                RabbitConfig.PUBLICAR_NUEVO_INSCRITO_QUEUE,
                gson.toJson(notificationNuevaInscripcion).getBytes()
        );
    }

    public void publicarEntregaTarea(NotificationTareaEntregada notificationTareaEntregada) {
        log.info(String.format(
                "Entrega hecha por estudiante %s emitida a queue VISTA PROFESOR",
                notificationTareaEntregada.getVistaEstudiante().get_id()));

        convertAndSend(
                RabbitConfig.PUBLICAR_ENTREGA_TAREA_QUEUE,
                gson.toJson(notificationTareaEntregada).getBytes());
    }
}
