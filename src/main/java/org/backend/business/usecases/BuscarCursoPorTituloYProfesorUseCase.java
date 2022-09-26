package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
public class BuscarCursoPorTituloYProfesorUseCase {
    private MongoViewRepository mongoViewRepository;

    private EncontrarCursoPorRegexUseCase encontrarCursoPorRegexUseCase;

    public BuscarCursoPorTituloYProfesorUseCase(MongoViewRepository mongoViewRepository, EncontrarCursoPorRegexUseCase encontrarCursoPorRegexUseCase) {
        this.mongoViewRepository = mongoViewRepository;
        this.encontrarCursoPorRegexUseCase = encontrarCursoPorRegexUseCase;
    }

    public Flux<VistaCurso> buscarCursoPorTituloYProfesorUseCase(String regex, String profesorID) {
        return this.encontrarCursoPorRegexUseCase.encontrarCursoPorRegexUseCase(regex)
                .filter(vistaCurso -> Objects.equals(vistaCurso.getProfesorID(), profesorID));

    }
}
