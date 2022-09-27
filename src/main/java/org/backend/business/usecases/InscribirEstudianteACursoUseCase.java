package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.domain.commands.CrearInscripcion;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class InscribirEstudianteACursoUseCase {

    private final MongoEventRepository mongoEventRepository;
    private final MongoViewRepository mongoViewRepository;

    public InscribirEstudianteACursoUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
    }

    public Mono<VistaEstudiante> apply(Mono<CrearInscripcion> crearInscripcionMono) {
        return crearInscripcionMono
                .flatMap(command -> {
                    InscripcionGeneric inscripcionGeneric = new InscripcionGeneric(
                            command.getCursoID(),
                            command.getNombreCurso()
                    );

                    // inscripcionGeneric.setCursoID(command.getCursoID());
                    // inscripcionGeneric.setPromedio((float) 0);
                    // inscripcionGeneric.setAvance((float) 0);
                    // inscripcionGeneric.setNombreCurso(command.getNombreCurso());
                    // inscripcionGeneric.setFechaInscripcion(LocalDate.now());
                    // inscripcionGeneric.setEstadosTarea(new HashSet<>());


                    //this.mongoViewRepository.agregarInscritoACurso(command.getEstudianteID(), command.getCursoID())

                    return this.mongoViewRepository
                            .listarTareasPorCurso(command.getCursoID())
                            .map(vistaTarea -> new EstadoTareaGeneric(
                                    vistaTarea.get_id(),
                                    vistaTarea.getTitulo(),
                                    vistaTarea.getFechaLimite())
                            )
                            .collect(Collectors.toSet())
                            .flatMap(estadoTareaGenerics -> {
                                inscripcionGeneric.setEstadosTarea(estadoTareaGenerics);

                                return this.mongoViewRepository
                                        .agregarInscripcion(inscripcionGeneric, command.getEstudianteID())
                                        .doOnTerminate(() -> this.mongoViewRepository
                                                .agregarInscritoACurso(command.getEstudianteID(), command.getCursoID()));
                            });
                });
    }
}
