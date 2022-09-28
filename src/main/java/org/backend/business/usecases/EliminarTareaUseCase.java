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


    public Mono<VistaTarea> apply(Mono<EliminarTarea> eliminarTareaMono){
        return eliminarTareaMono.flatMap(eliminarTarea ->
                this.mongoViewRepository.eliminarTarea(eliminarTarea.getTareaID()).doOnNext(vistaTarea ->
                        mongoViewRepository.eliminarTareaDeEstudiante(eliminarTarea.getTareaID(),eliminarTarea.getEstudianteID(), eliminarTarea.getCursoID())
                                .doOnNext( vistaCurso ->  mongoViewRepository.eliminarTareaDeCurso(eliminarTarea.getTareaID(), eliminarTarea.getCursoID())))

                );



    }
}
