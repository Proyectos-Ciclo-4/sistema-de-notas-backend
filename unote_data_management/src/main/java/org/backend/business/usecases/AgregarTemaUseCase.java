package org.backend.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.gateways.EventRepository;
import org.backend.business.models.vistasmaterializadas.Blockchain;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.Tema;
import org.backend.domain.commands.CrearTema;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Orden;
import org.backend.domain.valueobjects.Titulo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AgregarTemaUseCase {

    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;
    private final CrearTareaUseCase crearTareaUseCase;

    private final Blockchain blockchain;


    public AgregarTemaUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository, CrearTareaUseCase crearTareaUseCase,  Blockchain blockchain) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
        this.crearTareaUseCase = crearTareaUseCase;
        this.blockchain = blockchain;
    }

    public Mono<TemaGeneric> apply(Mono<CrearTema> crearTemaMono) {
        return crearTemaMono
                .flatMap(command -> {
                    var uuid =  UUID.randomUUID().toString();
                    Set<String> tareasIDS = new HashSet<>();

                    Tema temaAR = new Tema(
                            TemaID.of(uuid),
                            new Orden(command.getOrden()),
                            new Titulo(command.getTitulo()),
                            command.getTareas()

                    );
                    List<DomainEvent> events =  temaAR.getUncommittedChanges();
                    events.forEach(
                            mongoEventRepository::saveEvent
                    );


                    TemaGeneric newTema = new TemaGeneric(
                            uuid,
                            command.getCursoID(),
                            command.getOrden(),
                            command.getTitulo(),
                            tareasIDS
                    );

                    return this.mongoViewRepository.agregarTema(newTema)
                            .doOnNext(temaGeneric -> {

                                blockchain.saveBlock(newTema,"temaCreado", newTema.getTemaID());
                                if (!command.getTareas().isEmpty()) {
                                    command.getTareas().forEach(
                                            crearTarea -> {
                                                crearTarea.setCursoID(temaGeneric.getCursoID());
                                                crearTarea.setTemaID(temaGeneric.getTemaID());

                                                crearTareaUseCase
                                                        .apply(Mono.just(crearTarea))
                                                        .subscribe(vistaTarea -> tareasIDS.add(vistaTarea.get_id()));



                                            });

                                }
                            });
                });
    }
}
