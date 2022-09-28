package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EntregarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EntregarTareaUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<EstadoTareaGeneric> apply(String estadoTareaID, String archivoID) {

        return this.mongoViewRepository.entregarTarea(estadoTareaID, archivoID);

        /*
        return entregarTareaMono
                .flatMap(entregarTarea -> this.mongoViewRepository.entregarTarea(
                        entregarTarea.getEstudianteID(),
                        entregarTarea.getCursoID(),
                        entregarTarea.getTareaID(),
                        entregarTarea.getArchivoID())
                );

         */

    }
}
