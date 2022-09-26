package org.backend.application.handlers;


import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.awt.*;

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

    @Bean
    public RouterFunction<ServerResponse> encontrarEstudiantePorID(EncontrarEstudiantePorIDUseCase encontrarEstudiantePorIDUseCase){
        return route(
                GET("/buscarAlumno/{_id}"),
                request -> ServerResponse
                       .ok()
                       .contentType(MediaType.APPLICATION_JSON)
                       .body(BodyInserters.fromPublisher(
                               encontrarEstudiantePorIDUseCase.encontrarEstudiantePorID(
                                       request.pathVariable("_id")
                               ), VistaEstudiante.class
                       ))
                       .onErrorResume(throwable ->
                               ServerResponse.status(HttpStatus.NOT_FOUND).build())

        );
    }

    @Bean
    public RouterFunction<ServerResponse> encontrarCursoPorId(EncontrarCursoPorIdUseCase encontrarCursoPorIdUseCase){
        return route(
                GET("/buscarCurso/{_id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                encontrarCursoPorIdUseCase.encontrarCursoPorId(request.pathVariable("_id")),
                                VistaCurso.class
                        )).onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> encontrarCursoPorTitulo(EncontrarCursoPorRegexUseCase encontrarCursoPorRegexUseCase) {
        return route(
                GET("/buscarTituloCurso/{regex}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                encontrarCursoPorRegexUseCase.encontrarCursoPorRegexUseCase(request.pathVariable("regex")),
                                VistaCurso.class
                        )).onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }

    @Bean RouterFunction<ServerResponse> encontrarCursoPorTituloYProfesor(BuscarCursoPorTituloYProfesorUseCase buscarCursoPorTituloYProfesorUseCase) {
        return route(
                GET("/buscarCursoTituloProfesor/{regex}/{profesorID}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                buscarCursoPorTituloYProfesorUseCase
                                        .buscarCursoPorTituloYProfesorUseCase(
                                                request.pathVariable("regex"),
                                                request.pathVariable("profesorID")),
                                VistaCurso.class

                        )).onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }


    @Bean
    public RouterFunction<ServerResponse> encontrarProfesroPorId(EncontrarProfesorPorIdUseCase encontrarProfesorPorIdUseCase){
        return route(
                GET("/buscarProfesor/{_id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                encontrarProfesorPorIdUseCase.encontrarProfesorPorID(request.pathVariable("_id")),
                                VistaProfesor.class
                        ))
                        .onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> encontrarTareaPorId(EncontrarTareaPorIDUseCase encontrarTareaPorIDUseCase){
        return route(
                GET("/buscarTarea/{_id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                encontrarTareaPorIDUseCase.encontrarTareaPorID("_id"),
                                VistaTarea.class
                        ))
                        .onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
    @Bean
    public RouterFunction<ServerResponse> encontrarTodosLosProfesores(EncontrarTodosProfesoresUseCase encontrarTodosProfesoresUseCase) {
        return route(

                GET("/buscarProfesores"),
                request ->  ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                encontrarTodosProfesoresUseCase.encontrarTodosProfesores(),
                                VistaProfesor.class
                        ))
                        .onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
    @Bean
    public RouterFunction<ServerResponse> listarCursos(ListarCursosUseCase listarCursosUseCase) {
        return route(
                GET("/listarCursos"),
                request ->  ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                listarCursosUseCase.listarCursos(),
                                VistaCurso.class
                        ))
                        .onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> listarTareasPorCurso(ListarTareasPorCursoUseCase listarTareasPorCursoUseCase){
        return route(
                GET("/listarTareasCurso/{_id}"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(
                                listarTareasPorCursoUseCase.listarTareasPorCurso(request.pathVariable("_id")),
                                VistaTarea.class
                        ))
                        .onErrorResume(throwable ->
                                ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }

}
