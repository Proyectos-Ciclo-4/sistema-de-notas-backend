package org.backend.business.usecases;

import com.mongodb.MongoNodeIsRecoveringException;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.commands.CrearTarea;
import org.backend.domain.commands.CrearTema;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AgregarTemaUseCase {

    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;
    private final CrearTareaUseCase crearTareaUseCase;


    public AgregarTemaUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository, CrearTareaUseCase crearTareaUseCase) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
        this.crearTareaUseCase = crearTareaUseCase;
    }

    public Mono<TemaGeneric> apply(Mono<CrearTema> crearTemaMono) {
        return crearTemaMono
                .flatMap(command -> {
                    Set<String> tareasIDS = new HashSet<>();

                    if (command.getTareas().isEmpty()) {
                        command.getTareas().forEach(
                                crearTarea -> crearTareaUseCase
                                        .apply(Mono.just(crearTarea))
                                        .subscribe(vistaTarea -> tareasIDS.add(vistaTarea.getTareaID()))
                                );
                    }

                    TemaGeneric newTema = new TemaGeneric(
                            UUID.randomUUID().toString(),
                            command.getOrden(),
                            command.getTitulo(),
                            tareasIDS
                    );

                    return this.mongoViewRepository.agregarTema(newTema);
                });
    }
}
