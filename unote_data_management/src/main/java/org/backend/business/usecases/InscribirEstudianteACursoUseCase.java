package org.backend.business.usecases;

import org.backend.application.bus.RabbitMQEventBus;
import org.backend.application.bus.notificationmodels.NotificationNuevaInscripcion;
import org.backend.application.repository.MongoEventRepository;
import org.backend.application.repository.MongoViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.domain.commands.CrearInscripcion;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
public class InscribirEstudianteACursoUseCase {

  private final MongoEventRepository mongoEventRepository;
  private final MongoViewRepository mongoViewRepository;

  private final RabbitMQEventBus rabbitMQEventBus;

    public InscribirEstudianteACursoUseCase(MongoEventRepository mongoEventRepository, MongoViewRepository mongoViewRepository, RabbitMQEventBus rabbitMQEventBus) {
        this.mongoEventRepository = mongoEventRepository;
        this.mongoViewRepository = mongoViewRepository;
        this.rabbitMQEventBus = rabbitMQEventBus;
    }

    public Mono<VistaEstudiante> apply(Mono<CrearInscripcion> crearInscripcionMono) {
    return crearInscripcionMono
        .flatMap(command -> {
          InscripcionGeneric inscripcionGeneric = new InscripcionGeneric(
              command.getCursoID(),
              command.getNombreCurso()
          );

          return this.mongoViewRepository
              .listarTareasPorCurso(command.getCursoID())
              .map(vistaTarea -> new EstadoTareaGeneric(
                      vistaTarea.get_id(),
                      vistaTarea.getTitulo(),
                      vistaTarea.getTemaID(),
                      vistaTarea.getTemaNombre(),
                      vistaTarea.getFechaLimite(),
                      vistaTarea.getOrden()
                  )
              )
              .collect(Collectors.toSet())
              .flatMap(estadoTareaGenerics -> {
                inscripcionGeneric.setEstadosTarea(estadoTareaGenerics);

                return this.mongoViewRepository
                        .agregarInscripcion(inscripcionGeneric, command.getEstudianteID())
                        .doOnTerminate(() -> this.mongoViewRepository
                                .agregarInscritoACurso(command.getEstudianteID(), command.getCursoID()));
              })
                  .doOnSuccess(vistaEstudiante -> this.mongoViewRepository
                          .encontrarCursoPorId(command.getCursoID())
                          .subscribe(vistaCurso -> this.rabbitMQEventBus.publicarNuevoInscrito(
                                  new NotificationNuevaInscripcion(
                                          vistaCurso.getProfesorID(),
                                          vistaEstudiante))
                          ));
        });
  }
}
