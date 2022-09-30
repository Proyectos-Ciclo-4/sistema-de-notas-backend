package org.backend.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.domain.Profesor;
import org.backend.domain.commands.CrearProfesor;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Nombre;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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

                    Profesor profesorAR = new Profesor(
                            ProfesorID.of(command.getProfesorID()),
                            new Nombre(command.getNombre())
                    );

                    List<DomainEvent> events = profesorAR.getUncommittedChanges();
                    events.forEach(
                            mongoEventRepository::saveEvent
                    );
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
