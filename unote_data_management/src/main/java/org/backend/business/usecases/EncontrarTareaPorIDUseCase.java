package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class EncontrarTareaPorIDUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarTareaPorIDUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaTarea> encontrarTareaPorID(String tareaID){
        return mongoViewRepository.encontrarTareaPorID(tareaID);
    }
}
