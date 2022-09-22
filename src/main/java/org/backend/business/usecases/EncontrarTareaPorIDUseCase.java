package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import reactor.core.publisher.Mono;

public class EncontrarTareaPorIDUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarTareaPorIDUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaTarea> encontrarTareaPorID(String tareaID){
        return mongoViewRepository.encontrarTareaPorID(tareaID);
    }
}
