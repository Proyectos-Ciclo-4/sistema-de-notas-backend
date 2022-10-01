package org.backend.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.Estudiante;
import org.backend.domain.commands.CrearEstudiante;
import org.backend.domain.identifiers.EstudianteID;
import org.backend.domain.valueobjects.Nombre;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import java.util.HashMap;
import java.util.List;


@Service
public class CrearEstudianteUseCase {
    private final MongoViewRepository mongoViewRepository;
    private final MongoEventRepository mongoEventRepository;

    public CrearEstudianteUseCase(MongoViewRepository mongoViewRepository, MongoEventRepository mongoEventRepository) {
        this.mongoViewRepository = mongoViewRepository;
        this.mongoEventRepository = mongoEventRepository;
    }

    public Mono<VistaEstudiante> apply(Mono<CrearEstudiante> crearEstudianteMono){
        return crearEstudianteMono.flatMap(command -> {

            /*Estudiante estudinateAR = new Estudiante(
                    EstudianteID.of(command.getEstudianteID()),
                    new Nombre(command.getNombre()),
                    new HashMap<>()
            );
            List<DomainEvent> events = estudinateAR.getUncommittedChanges();
            events.forEach(
                    mongoEventRepository::saveEvent
            );*/
                    VistaEstudiante estudiante = new VistaEstudiante(
                            command.getEstudianteID(),
                            command.getNombre()
                    );

                    return mongoViewRepository.crearEstudiante(estudiante);
                }
        );
    }
}
