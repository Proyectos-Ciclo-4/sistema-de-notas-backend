package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import reactor.core.publisher.Flux;
import org.springframework.stereotype.Service;
@Service
public class EncontrarTodosEstudiantesUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarTodosEstudiantesUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaEstudiante> encontrarTodosEstudiantes(){
        return this.mongoViewRepository.encontrarTodosEstudiantes();
    }
}
