package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EncontrarProfesorPorIdUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarProfesorPorIdUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaProfesor> encontrarProfesorPorID(String profesroID){
        return this.mongoViewRepository.encontrarProfesorPorID(profesroID);
    }
}
