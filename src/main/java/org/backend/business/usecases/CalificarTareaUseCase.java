package org.backend.business.usecases;

import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.commands.CalificarTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CalificarTareaUseCase {
    private final MongoViewRepository mongoViewRepository;
    //private final SocketEstudianteHandler socketEstudianteHandler;

    public CalificarTareaUseCase(MongoViewRepository mongoViewRepository  /*, SocketEstudianteHandler socketEstudianteHandler*/) {
        this.mongoViewRepository = mongoViewRepository;
        //this.socketEstudianteHandler = socketEstudianteHandler;
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
                //.flatMap(socketEstudianteHandler::emitirCalificacionActualizada);
    }
}
