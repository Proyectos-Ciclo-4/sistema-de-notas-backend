package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.bus.notificationmodels.NotificationTareaActualizada;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.commands.CalificarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CalificarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;
    private final RabbitMQEventBus rabbitMQEventBus;

    private final Blockchain blockchain;

    public CalificarTareaUseCase(MongoViewRepository mongoViewRepository, RabbitMQEventBus rabbitMQEventBus, Blockchain blockchain) {
        this.mongoViewRepository = mongoViewRepository;
        this.rabbitMQEventBus = rabbitMQEventBus;
        this.blockchain = blockchain;
    }

    public Mono<VistaEstudiante> apply(Mono<CalificarTarea> calificarTareaMono) {
        return calificarTareaMono
                .flatMap(command ->
                        mongoViewRepository.calificarTarea(
                                command.getEstudianteID(),
                                command.getCursoID(),
                                command.getTareaID(),
                                command.getCalificacion(),
                                command.getRetroalimentacion()
                        )
                                .doOnSuccess(vistaEstudiante ->
                                        rabbitMQEventBus.publicarCalificacion(
                                                new NotificationTareaActualizada(
                                                        vistaEstudiante.get_id(),
                                                        vistaEstudiante
                                                                .encontrarInscripcion(command.getCursoID())
                                                                .encontrarEstadoTarea(command.getTareaID()),
                                                        command.getCursoID()
                                                        )
                                        ))
                ).doOnSuccess(vistaEstudiante -> blockchain.saveBlock(vistaEstudiante,"unote.tareaCalificada",vistaEstudiante.get_id()));
    }
}
