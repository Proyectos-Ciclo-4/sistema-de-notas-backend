package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.domain.commands.CalificarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CalificarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;

    public CalificarTareaUseCase(MongoViewRepository mongoViewRepository) {
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaEstudiante> apply(Mono<CalificarTarea> calificarTareaMono) {
        return calificarTareaMono
                .flatMap(command ->
                        mongoViewRepository.calificarTarea(
                                command.getEstudianteID(),
                                command.getCursoID(),
                                command.getTareaID(),
                                command.getCalificacion(),
                                command.getRetroalimentacion()
                        ));
    }
}