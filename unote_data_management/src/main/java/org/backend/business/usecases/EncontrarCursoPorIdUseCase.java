package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class EncontrarCursoPorIdUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarCursoPorIdUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaCurso> encontrarCursoPorId(String cursoID){
        return this.mongoViewRepository.encontrarCursoPorId(cursoID);
    }
}
