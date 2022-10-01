package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.domain.commands.CrearTarea;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CrearTareaUseCase {
    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;

    private final Blockchain blockchain;

    public CrearTareaUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository, Blockchain blockchain) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
        this.blockchain = blockchain;
    }

    public Mono<VistaTarea> apply(Mono<CrearTarea> crearTareaMono) {
        return crearTareaMono
                .flatMap(command -> {
                    String uuid = UUID.randomUUID().toString();

                    VistaTarea nuevaTarea = new VistaTarea(
                            uuid,
                            command.getCursoID(),
                            command.getTemaID(),
                            command.getTemaNombre(),
                            command.getTitulo(),
                            command.getDescripcion(),
                            command.getOrden(),
                            command.getFechaLimite(),
                            command.getPorcentaje()
                    );
                    blockchain.saveBlock(nuevaTarea, "tareaCreada" , nuevaTarea.get_id());
                    return mongoViewRepository
                            .crearTarea(nuevaTarea)
                            .doOnNext(vistaTarea ->
                                    mongoViewRepository.agregarTareaATema(nuevaTarea))
                            .doOnSuccess(vistaTarea ->
                                    mongoViewRepository.agregarTareaAInscripcion(
                                            vistaTarea.getCursoID(),
                                            new EstadoTareaGeneric(
                                                    vistaTarea.get_id(),
                                                    vistaTarea.getTitulo(),
                                                    vistaTarea.getTemaID(),
                                                    vistaTarea.getTemaNombre(),
                                                    vistaTarea.getFechaLimite(),
                                                    vistaTarea.getOrden()
                                            ))
                            );

                    /*
                    Tema temaAR = new Tema(TemaID.of(command.getTemaID()));
                    temaAR.crearTarea(
                            new Titulo(command.getTitulo()),
                            new Descripcion(command.getDescripcion()),
                            new Orden(command.getOrden()),
                            new FechaLimite(command.getFechaLimite()),
                            command.getPorcentaje(),
                            CursoID.of(command.getCursoID())
                    );

                    List<DomainEvent> events = temaAR.getUncommittedChanges();
                     */
                });
    }
}
