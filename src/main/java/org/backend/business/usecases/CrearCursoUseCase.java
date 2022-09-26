package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.commands.CrearCurso;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
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

                    VistaCurso newCurso = new VistaCurso(
                            UUID.randomUUID().toString(),
                            command.getTitulo(),
                            command.getProfesorID(),
                            temas
                    );

                    return this.mongoViewRepository
                            .crearCurso(newCurso)
                            .doOnNext(vistaCurso -> {
                                if (!command.getTemas().isEmpty()) {
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
