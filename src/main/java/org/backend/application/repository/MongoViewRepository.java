package org.backend.application.repository;

import lombok.extern.slf4j.Slf4j;
import org.backend.business.gateways.ViewRepository;
import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class MongoViewRepository implements ViewRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public MongoViewRepository(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<VistaProfesor> crearProfesor(VistaProfesor vistaProfesor) {
        return null;
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'PROFESOR' */

    @Override
    public Flux<VistaProfesor> encontrarTodosProfesores() {
        return reactiveMongoTemplate
                .findAll(VistaProfesor.class)
                .doOnComplete(
                        () -> log.info("Base de datos regresó todos los profesores.")
                ).doOnError(
                        throwable -> log.error(throwable.getMessage())
                );
    }

    @Override
    public Mono<VistaProfesor> encontrarProfesorPorID(String profesorID) {
        return null;
    }

    @Override
    public Mono<VistaProfesor> agregarNuevoCursoID(String profesorID, String cursoID) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> crearEstudiante(VistaEstudiante vistaEstudiante) {
        return null;
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'ESTUDIANTE' */

    @Override
    public Flux<VistaEstudiante> encontrarTodosEstudiantes() {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> encontrarEstudiantePorID(String estudianteID) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> agregarInscripcion(InscripcionGeneric inscripcionGeneric, String estudianteID) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarPromedio(String cursoID, Float promedio) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarAvance(String cursoID, Float avance) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarCalificacion(String cursoID, String tareaID, Integer calificacion) {
        return null;
    }

    @Override
    public Mono<VistaEstudiante> actualizarTarea(String cursoID, String tareaID, EstadoTareaGeneric estadoTareaGeneric) {
        return null;
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'CURSO' */

    @Override
    public Mono<VistaCurso> encontrarCursoPorId(String cursoID) {
        return null;
    }

    @Override
    public Flux<VistaCurso> listarCursos() {
        return null;
    }

    @Override
    public Mono<VistaCurso> crearCurso(VistaCurso curso) {
        return null;
    }

    @Override
    public Mono<VistaCurso> agregarTema(TemaGeneric nuevoTema) {
        return null;
    }

    @Override
    public Mono<VistaCurso> agregarTareaID(String tareaID) {
        return null;
    }

    /* OPERACIONES CON VISTA MATERIALIZADA 'TAREA' */

    @Override
    public Mono<VistaTarea> encontrarTareaPorID(String tareaID) {
        return null;
    }

    @Override
    public Flux<VistaTarea> listarTareasPorCurso(String cursoID) {
        return null;
    }

    @Override
    public Mono<VistaTarea> crearTarea(VistaTarea vistaTarea) {
        return null;
    }
}
