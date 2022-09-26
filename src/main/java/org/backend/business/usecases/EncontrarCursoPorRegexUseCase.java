package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EncontrarCursoPorRegexUseCase {
    private final MongoViewRepository mongoViewRepository;

    public EncontrarCursoPorRegexUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaCurso> encontrarCursoPorRegexUseCase(String regex) {
        return this.mongoViewRepository.encontrarCursoPorRegex(regex);

    }
}
