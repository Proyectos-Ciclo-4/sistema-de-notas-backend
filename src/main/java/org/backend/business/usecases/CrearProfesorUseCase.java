package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.generic.UseCaseForCommand;
import org.backend.business.models.vistasmaterializadas.VistaMaterializada;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.domain.commands.CrearProfesor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrearProfesorUseCase extends UseCaseForCommand<CrearProfesor> {

    // Repositorios
    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;

    public CrearProfesorUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
    }

    @Override
    public Flux<VistaProfesor> apply(Mono<CrearProfesor> crearProfesorMono) {
        return null;
    }
}
