package org.backend.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.Curso;
import org.backend.domain.commands.CrearCurso;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.valueobjects.Titulo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CrearCursoUseCase {
    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;

    private final AgregarTemaUseCase agregarTemaUseCase;

    public CrearCursoUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository, AgregarTemaUseCase agregarTemaUseCase) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
        this.agregarTemaUseCase = agregarTemaUseCase;
    }

    public Mono<VistaCurso> apply(Mono<CrearCurso> crearCursoMono) {
        return crearCursoMono
                .flatMap(command -> {
                    Set<TemaGeneric> temas = new HashSet<>();
                    var uuid =  UUID.randomUUID().toString();

                    Curso cursoAR = new Curso(
                            CursoID.of(uuid),
                            new Titulo(command.getTitulo()),
                            temas,
                            ProfesorID.of(command.getProfesorID())

                    );

                    List<DomainEvent> events =  cursoAR.getUncommittedChanges();
                    events.forEach(
                            mongoEventRepository::saveEvent
                    );

                    VistaCurso newCurso = new VistaCurso(
                            uuid,
                            command.getTitulo(),
                            command.getProfesorID()
                    );

                    return this.mongoViewRepository
                            .crearCurso(newCurso)
                            .doOnNext(vistaCurso -> {
                                if (command.checkTemasExists()) {
                                    command.getTemas().forEach(
                                            crearTema -> {
                                                crearTema.setCursoID(vistaCurso.get_id());

                                                agregarTemaUseCase
                                                        .apply(Mono.just(crearTema))
                                                        .subscribe(temaGeneric -> vistaCurso.agregarTema(temaGeneric));
                                            });
                                }
                            });
                });
    }
}
