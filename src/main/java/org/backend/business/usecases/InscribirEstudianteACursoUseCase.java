package org.backend.business.usecases;

import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.domain.commands.CrearInscripcion;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collector;
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
                            this.mongoViewRepository
                                    .listarTareasPorCurso(command.getCursoID())
                                    .map(vistaTarea -> new EstadoTareaGeneric(vistaTarea.get_id()))
                                    .collect(Collectors.toSet())
                                    // Es válido emplear .block() acá, porque es necesario
                                    // tener todos los items para armar la lista y poder usarla
                                    // en el constructor.
                                    .block()
                    );

                    return this.mongoViewRepository.agregarInscripcion(inscripcionGeneric, command.getEstudianteID());
                });
    }
}
