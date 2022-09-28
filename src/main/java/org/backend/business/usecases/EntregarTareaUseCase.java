package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EntregarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EntregarTareaUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<String> apply(Mono<String> archivoID) {
        //this.mongoViewRepository
        return Mono.just("lol");
    }
}
