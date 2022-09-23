package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.domain.commands.CrearProfesor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CrearProfesorUseCase {

    // Repositorios
    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;

    public CrearProfesorUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaProfesor> apply(Mono<CrearProfesor> crearProfesorMono) {
        return crearProfesorMono
                .flatMap(command -> {
                            VistaProfesor nuevoProfesor = new VistaProfesor(
                                    // Profesor y Estudiante son los únicos que van a recibir el
                                    // ID proveniente del frontend, en tanto que son los mismos IDs
                                    // de sus usuarios correspondientes, generados por Firebase
                                    command.getProfesorID(),
                                    command.getNombre()
                            );

                            return mongoViewRepository.crearProfesor(nuevoProfesor);
                        }
                );
    }
}
