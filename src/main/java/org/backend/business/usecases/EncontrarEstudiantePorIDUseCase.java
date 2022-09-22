package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import reactor.core.publisher.Mono;

public class EncontrarEstudiantePorIDUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarEstudiantePorIDUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaEstudiante> encontrarEstudiantePorID(String estudianteID){
        return this.mongoViewRepository.encontrarEstudiantePorID(estudianteID);
    }
}
