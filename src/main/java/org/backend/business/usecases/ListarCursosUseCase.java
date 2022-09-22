package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import reactor.core.publisher.Flux;

public class ListarCursosUseCase {
    private final MongoViewRepository mongoViewRepository;

    public ListarCursosUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaCurso> listarCursos(){
        return this.mongoViewRepository.listarCursos();
    }
}
