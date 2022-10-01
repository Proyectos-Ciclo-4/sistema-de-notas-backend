package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class EncontrarInscripcionPorCursoUseCase {

    private final MongoViewRepository mongoViewRepository;
    private final EncontrarCursoPorRegexUseCase encontrarCursoPorRegexUseCase;

    public EncontrarInscripcionPorCursoUseCase(MongoViewRepository mongoViewRepository, EncontrarCursoPorRegexUseCase encontrarCursoPorRegexUseCase) {
        this.mongoViewRepository = mongoViewRepository;
        this.encontrarCursoPorRegexUseCase = encontrarCursoPorRegexUseCase;
    }

    public Flux<InscripcionGeneric> encontrarInscripcionPorCursoUseCase(String regex, String estudianteID) {
        return this.mongoViewRepository.encontrarCursoPorRegex(regex)
                .filter(vistaCurso -> vistaCurso.revisarInscripcion(estudianteID))
                .flatMap(vistaCurso -> this.mongoViewRepository
                        .encontrarEstudiantePorID(estudianteID)
                        .map(vistaEstudiante ->
                                vistaEstudiante.encontrarInscripcion(vistaCurso.get_id())));

    }


}
