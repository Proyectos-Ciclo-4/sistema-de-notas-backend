package org.backend.application.handlers;

import co.com.sofka.domain.generic.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.usecases.CrearEstudianteUseCase;
import org.backend.business.usecases.CrearProfesorUseCase;
import org.backend.domain.commands.CrearEstudiante;
import org.backend.domain.commands.CrearProfesor;
import org.backend.domain.events.EstudianteCreado;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
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
    public  RouterFunction<ServerResponse> crearProfesor(CrearProfesorUseCase crearProfesorUseCase) {
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


}
