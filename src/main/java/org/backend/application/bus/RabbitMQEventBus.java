package org.backend.application.bus;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.backend.application.config.RabbitConfig;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.domain.valueobjects.EstadoTarea;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.GenericSignatureFormatError;

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

    public void publicarTareaNueva(VistaTarea vistaTarea){
        log.info(String.format("Tarea %s emitida a queue VISTA ESTUDIANTE", vistaTarea.get_id()));

        convertAndSend(
                RabbitConfig.VISTA_ESTUDIANTE_QUEUE_KEY,
                gson.toJson(vistaTarea).getBytes());
    }

    public void publicarCalificacion(EstadoTareaGeneric estadoTareaGeneric) {
        log.info(String.format("Estado tarea con calificacion %s emitida a queue VISTA ESTUDIANTE", estadoTareaGeneric.getCalificacion()));

        convertAndSend(
                RabbitConfig.VISTA_ESTUDIANTE_QUEUE_KEY,
                gson.toJson(estadoTareaGeneric).getBytes());
    }

    public void publicarNuevoInscrito(InscripcionGeneric inscripcionGeneric) {
        log.info(String.format("Inscripción en curso %s emitida a queue VISTA PROFESOR", inscripcionGeneric.getCursoID()));

        convertAndSend(
                RabbitConfig.VISTA_PROFESOR_QUEUE_KEY,
                gson.toJson(inscripcionGeneric).getBytes()
        );

    }

    public void publicarEntregaTarea(VistaEstudiante vistaEstudiante) {
        log.info(String.format("Entrega hecha por estudiante %s emitida a queue VISTA PROFESOR", vistaEstudiante.getNombre()));

        convertAndSend(
                RabbitConfig.VISTA_PROFESOR_QUEUE_KEY,
                gson.toJson(vistaEstudiante).getBytes());
    }
}
