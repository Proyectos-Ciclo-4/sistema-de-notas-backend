package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
@Service
public class ListarTareasPorCursoUseCase {
    private final MongoViewRepository mongoViewRepository;

    public ListarTareasPorCursoUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Flux<VistaTarea> listarTareasPorCurso(String cursoId){
        return this.mongoViewRepository.listarTareasPorCurso(cursoId);
    }
}
