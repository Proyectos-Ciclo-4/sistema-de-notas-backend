package org.backend.application.handlers;


import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class QueryHandler {

    @Bean
    public RouterFunction<ServerResponse> encontrarTodosLosEstudiantes(EncontrarTodosEstudiantesUseCase encontrarTodosEstudiantesUseCase) {
        return route(
                GET("/buscarAlumnos"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                encontrarTodosEstudiantesUseCase.encontrarTodosEstudiantes(),
                                VistaEstudiante.class))
                        .onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }



                        /*
                        encontrarTodosEstudiantesUseCase.encontrarTodosEstudiantes()
                        .flatMap(vistaEstudiantes ->
                                    ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(vistaEstudiantes)
                                )

                         */
    @Bean
    public RouterFunction<ServerResponse> encontrarEstudiantePorID(EncontrarEstudiantePorIDUseCase encontrarEstudiantePorIDUseCase){
        return route(
                GET("/buscarAlumno/{_id}"),
                request -> encontrarEstudiantePorIDUseCase.encontrarEstudiantePorID(request.pathVariable("_id"))
                        .flatMap(
                                vistaEstudiante ->
                                        ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(vistaEstudiante)
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> encontrarCursoPorId(EncontrarCursoPorIdUseCase encontrarCursoPorIdUseCase){
        return route(
                GET("/buscarCurso/{_id}"),
                request -> encontrarCursoPorIdUseCase.encontrarCursoPorId(request.pathVariable("_id"))
                        .flatMap(
                                vistaCurso -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(vistaCurso)
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> encontrarProfesroPorId(EncontrarProfesorPorIdUseCase encontrarProfesorPorIdUseCase){
        return route(
                GET("/buscarProfesor/{_id}"),
                request -> encontrarProfesorPorIdUseCase.encontrarProfesorPorID(request.pathVariable("_id"))
                        .flatMap(
                                vistaProfesor -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(vistaProfesor)
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> encontrarTareaPorId(EncontrarTareaPorIDUseCase encontrarTareaPorIDUseCase){
        return route(
                GET("/buscarTarea/{_id}"),
                request -> encontrarTareaPorIDUseCase.encontrarTareaPorID(request.pathVariable("_id"))
                        .flatMap(
                                vistaTarea -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(vistaTarea)
                        )
        );
    }
    @Bean
    public RouterFunction<ServerResponse> encontrarTodosLosProfesores(EncontrarTodosProfesoresUseCase encontrarTodosProfesoresUseCase) {
        return route(

                GET("/buscarProfesores"),
                request ->  encontrarTodosProfesoresUseCase.encontrarTodosProfesores()
                        .collectList()
                        .flatMap(vistaProfesors ->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(Mono.just(vistaProfesors))
                        )
        );
    }
    @Bean
    public RouterFunction<ServerResponse> listarCursos(ListarCursosUseCase listarCursosUseCase) {
        return route(

                GET("/listarCursos"),
                request ->  listarCursosUseCase.listarCursos()
                        .collectList()
                        .flatMap(vistaCursos ->
                                ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(Mono.just(vistaCursos))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> listarTareasPorCurso(ListarTareasPorCursoUseCase listarTareasPorCursoUseCase){
        return route(
                GET("/listarTareasCurso/{_id}"),
                request -> listarTareasPorCursoUseCase.listarTareasPorCurso(request.pathVariable("_id"))
                        .collectList()
                        .flatMap(
                                vistaTareas -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(vistaTareas)
                        )
        );
    }

}
