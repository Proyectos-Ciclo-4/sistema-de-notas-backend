package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.domain.commands.CrearEstudiante;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class CrearEstudianteUseCase {
    private final MongoViewRepository mongoViewRepository;
    private final MongoEventRepository mongoEventRepository;

    private final Blockchain blockchain;

    public CrearEstudianteUseCase(MongoViewRepository mongoViewRepository, MongoEventRepository mongoEventRepository, Blockchain blockchain) {
        this.mongoViewRepository = mongoViewRepository;
        this.mongoEventRepository = mongoEventRepository;
        this.blockchain = blockchain;
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
                    blockchain.saveBlock(estudiante,"estudianteCreado",estudiante.get_id());
                    return mongoViewRepository.crearEstudiante(estudiante);
                }
        );
    }
}
