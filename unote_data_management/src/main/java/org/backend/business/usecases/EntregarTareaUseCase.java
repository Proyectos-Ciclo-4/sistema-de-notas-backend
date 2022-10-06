package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.bus.notificationmodels.NotificationTareaEntregada;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.commands.EntregarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EntregarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;
    private final RabbitMQEventBus rabbitMQEventBus;

    private final Blockchain blockchain;

    public EntregarTareaUseCase(MongoViewRepository mongoViewRepository, RabbitMQEventBus rabbitMQEventBus, Blockchain blockchain) {
        this.mongoViewRepository = mongoViewRepository;
        this.rabbitMQEventBus = rabbitMQEventBus;
        this.blockchain = blockchain;
    }

    public Mono<EstadoTareaGeneric> apply(Mono<EntregarTarea> entregarTareaMono) {
        return entregarTareaMono
                .flatMap(entregarTarea ->
                        mongoViewRepository.entregarTarea(
                                entregarTarea.getEstudianteID(),
                                entregarTarea.getCursoID(),
                                entregarTarea.getTareaID(),
                                entregarTarea.getArchivoURL()
                        )
                                .doOnSuccess(estadoTareaGeneric -> this.mongoViewRepository
                                                .encontrarCursoPorId(entregarTarea.getCursoID())
                                                .subscribe(vistaCurso -> this.rabbitMQEventBus.publicarEntregaTarea(
                                                        new NotificationTareaEntregada(
                                                                vistaCurso.getProfesorID(),
                                                                estadoTareaGeneric,
                                                                vistaCurso.get_id()
                                                        )
                                                ))
                                        )
                                /*.doOnNext(vistaEstudiante -> entregarTareaMono
                                        .subscribe(entregarTarea -> this.mongoViewRepository
                                                .encontrarCursoPorId(entregarTarea.getCursoID())
                                                .subscribe(vistaCurso -> this.rabbitMQEventBus.publicarEntregaTarea(
                                                        new NotificationTareaEntregada(
                                                                vistaCurso.getProfesorID(),
                                                                vistaEstudiante
                                                        )
                                                ))
                                        )
                                )
                                 */
                ).doOnSuccess(vistaEstudiante -> blockchain.saveBlock(vistaEstudiante,"unote.tareaEntregada", vistaEstudiante.get_id()));
    }
}
