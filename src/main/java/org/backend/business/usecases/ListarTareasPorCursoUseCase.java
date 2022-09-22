package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import reactor.core.publisher.Flux;

public class ListarTareasPorCursoUseCase {
    private final MongoViewRepository mongoViewRepository;

    public ListarTareasPorCursoUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaTarea> listarTareasPorCurso(String cursoId){
        return this.mongoViewRepository.listarTareasPorCurso(cursoId);
    }
}
