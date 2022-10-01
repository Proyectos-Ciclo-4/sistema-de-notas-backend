package org.backend.application.handlers;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.business.usecases.*;
import org.backend.domain.commands.*;
import org.backend.domain.events.EstudianteCreado;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class CommandHandle {

    @Bean
    public RouterFunction<ServerResponse> crearEstudiante(CrearEstudianteUseCase crearEstudianteUseCase) {
        return route(
                POST("/crearEstudiante")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> crearEstudianteUseCase.apply(request.bodyToMono(CrearEstudiante.class))
                                .flatMap(vistaEstudiante -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(vistaEstudiante))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearProfesor(CrearProfesorUseCase crearProfesorUseCase) {
        return route(
                POST("/crearProfesor")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> crearProfesorUseCase.apply(request.bodyToMono(CrearProfesor.class))
                        .flatMap(vistaProfesor -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(vistaProfesor))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearCurso(CrearCursoUseCase crearCursoUseCase) {
        return route(
                POST("/crearCurso")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> crearCursoUseCase.apply(request.bodyToMono(CrearCurso.class))
                        .flatMap(vistaCurso -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(vistaCurso))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearTema(AgregarTemaUseCase agregarTemaUseCase) {
        return route(
                POST("/crearTema")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> agregarTemaUseCase.apply(request.bodyToMono(CrearTema.class))
                        .flatMap(temaGeneric -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(temaGeneric))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearTarea(CrearTareaUseCase crearTareaUseCase) {
        return route(
                POST("/crearTarea")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> crearTareaUseCase.apply(request.bodyToMono(CrearTarea.class))
                        .flatMap(vistaTarea -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(vistaTarea))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> inscribirEstudianteEnCurso(InscribirEstudianteACursoUseCase inscribirEstudianteEnCurso) {
        return route(
                POST("/inscribirEstudiante")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> inscribirEstudianteEnCurso.apply(request.bodyToMono(CrearInscripcion.class))
                        .flatMap(vistaEstudiante -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(vistaEstudiante))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());
                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> entregarTarea(EntregarTareaUseCase entregarTareaUseCase) {
        return route(
                POST("/entregarTarea"),
                request -> entregarTareaUseCase.apply(
                        request.bodyToMono(EntregarTarea.class))
                        .flatMap(vistaEstudiante -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(vistaEstudiante))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());

                            return ServerResponse.badRequest().build();
                        })
        );
    }

    @Bean
    public RouterFunction<ServerResponse> eliminarTarea(EliminarTareaUseCase eliminarTareaUseCase) {
        return route(
                POST("/eliminarTarea"),
                request -> eliminarTareaUseCase.apply(
                        request.bodyToMono(EliminarTarea.class))
                        .flatMap(eliminarTarea -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(eliminarTarea))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());

                            return ServerResponse.badRequest().build();
                        })
        );
    }

   /* @Bean
    public RouterFunction<ServerResponse> eliminarTema(EliminarTemaUseCase eliminarTemaUseCase) {
        return route(
                DELETE("/eliminarTema"),
                request -> eliminarTemaUseCase.apply(
                        request.bodyToMono(EliminarTema.class))
                        .flatMap(eliminarTema -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(eliminarTema))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());

                            return ServerResponse.badRequest().build();
                        })
        );
    }*/

    @Bean
    public RouterFunction<ServerResponse> calificarTarea(CalificarTareaUseCase calificarTareaUseCase) {
        return route(
                POST("/calificarTarea"),
                request -> calificarTareaUseCase.apply(
                        request.bodyToMono(CalificarTarea.class))
                        .flatMap(vistaEstudiante -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(vistaEstudiante))
                        .onErrorResume(throwable -> {
                            log.error(throwable.getMessage());

                            return ServerResponse.badRequest().build();
                        })

        );

    }

}
