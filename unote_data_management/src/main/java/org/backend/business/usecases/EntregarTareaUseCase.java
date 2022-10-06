package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.bus.notificationmodels.NotificationTareaEntregada;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.domain.commands.EntregarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EntregarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;
    private final RabbitMQEventBus rabbitMQEventBus;

    public EntregarTareaUseCase(MongoViewRepository mongoViewRepository, RabbitMQEventBus rabbitMQEventBus) {
        this.mongoViewRepository = mongoViewRepository;
        this.rabbitMQEventBus = rabbitMQEventBus;
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
                );
    }
}
