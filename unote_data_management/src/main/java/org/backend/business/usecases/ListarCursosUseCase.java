package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
@Service
public class ListarCursosUseCase {
    private final MongoViewRepository mongoViewRepository;

    public ListarCursosUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaCurso> listarCursos(){
        return this.mongoViewRepository.listarCursos();
    }
}
