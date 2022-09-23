package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.domain.commands.CrearTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CrearTareaUseCase {
    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;

    public CrearTareaUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaTarea> apply(Mono<CrearTarea> crearTareaMono) {
        return crearTareaMono
                .flatMap(command -> {
                    VistaTarea nuevaTarea = new VistaTarea(
                            UUID.randomUUID().toString(),
                            command.getFechaLimite(),
                            command.getPorcentaje()
                    );

                    return  mongoViewRepository.crearTarea(nuevaTarea);
                });
    }
}
