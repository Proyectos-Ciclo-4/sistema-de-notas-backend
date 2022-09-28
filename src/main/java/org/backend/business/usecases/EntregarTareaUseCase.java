package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.domain.commands.EntregarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EntregarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EntregarTareaUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaEstudiante> apply(Mono<EntregarTarea> entregarTareaMono) {
        return entregarTareaMono
                .flatMap(entregarTarea ->
                        mongoViewRepository.entregarTarea(
                                entregarTarea.getEstudianteID(),
                                entregarTarea.getCursoID(),
                                entregarTarea.getTareaID(),
                                entregarTarea.getURLArchivo()
                        ));

    }
}
