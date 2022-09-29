package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.domain.commands.EliminarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EliminarTareaUseCase {

    private final MongoViewRepository mongoViewRepository;

    public EliminarTareaUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }


    public Mono<EliminarTarea> apply(Mono<EliminarTarea> eliminarTareaMono){
        return eliminarTareaMono
                .doOnNext(eliminarTarea -> {
                    this.mongoViewRepository.eliminarTareaDeEstudiante(
                            eliminarTarea.getCursoID(),
                            eliminarTarea.getTareaID(),
                            eliminarTarea.getTemaID()
                    );
                })
                .doOnNext(eliminarTarea -> {
                    this.mongoViewRepository.eliminarTareaDeCurso(
                            eliminarTarea.getCursoID(),
                            eliminarTarea.getTareaID(),
                            eliminarTarea.getTemaID()
                    );
                })
                .doOnNext(eliminarTarea -> {
                            this.mongoViewRepository.eliminarVistaTarea(eliminarTarea.getTareaID());
                });

    }
}
