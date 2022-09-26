package org.backend.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.domain.Tema;
import org.backend.domain.commands.CrearTarea;
import org.backend.domain.entities.Tarea;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.FechaLimite;
import org.backend.domain.valueobjects.Titulo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
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

                    var uuid = UUID.randomUUID().toString();
                    Tema temaAR = new Tema(TemaID.of(command.getTemaID()));
                    temaAR.crearTarea(
                            new Titulo(command.getTitulo()),
                            new FechaLimite(command.getFechaLimite()),
                            command.getPorcentaje(),
                            CursoID.of(command.getCursoID())
                    );

                    List<DomainEvent> events = temaAR.getUncommittedChanges();

                    VistaTarea nuevaTarea = new VistaTarea(
                            uuid,
                            command.getCursoID(),
                            command.getTemaID(),
                            command.getTitulo(),
                            command.getFechaLimite(),
                            command.getPorcentaje()
                    );

                    return  mongoViewRepository.crearTarea(nuevaTarea);
                });
    }
}
