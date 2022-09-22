package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EncontrarTodosProfesoresUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarTodosProfesoresUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaProfesor> encontrarTodosProfesores() {
        return this.mongoViewRepository.encontrarTodosProfesores();
    }
}
