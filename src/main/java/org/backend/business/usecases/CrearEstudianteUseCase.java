package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.commands.CrearEstudiante;
import org.backend.domain.commands.CrearProfesor;
import reactor.core.publisher.Mono;

public class CrearEstudianteUseCase {
    private final MongoViewRepository mongoViewRepository;
    private final MongoEventRepository mongoEventRepository;

    public CrearEstudianteUseCase(MongoViewRepository mongoViewRepository, MongoEventRepository mongoEventRepository) {
        this.mongoViewRepository = mongoViewRepository;
        this.mongoEventRepository = mongoEventRepository;
    }

    public Mono<VistaEstudiante> apply(Mono<CrearEstudiante> crearEstudianteMono){
        return crearEstudianteMono.flatMap(command -> {
                    VistaEstudiante estudiante = new VistaEstudiante(
                            command.getEstudianteID()

                    );
                    return mongoViewRepository.crearEstudiante(estudiante);
                }

        );
    }
}
