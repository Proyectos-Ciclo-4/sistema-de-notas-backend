package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EncontrarEstudiantesEnCursoUseCase {

    private MongoViewRepository mongoViewRepository;

    public EncontrarEstudiantesEnCursoUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaEstudiante> encontrarEstudiantesEnCursoUseCase(String cursoID) {
        return this.mongoViewRepository
    }
}
