package org.backend.business.gateways;

import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ViewRepository {

    // Vista para profesor

    Mono<VistaProfesor> crearProfesor(VistaProfesor vistaProfesor);

    Flux<VistaProfesor> encontrarTodosProfesores();

    Mono<VistaProfesor> encontrarProfesorPorID(String profesorID);

    Mono<VistaProfesor> agregarNuevoCursoID(String profesorID, String cursoID);

    // Vista para estudiante

    Mono<VistaEstudiante> crearEstudiante(VistaEstudiante vistaEstudiante);

    Flux<VistaEstudiante> encontrarTodosEstudiantes();

    Mono<VistaEstudiante> encontrarEstudiantePorID(String estudianteID);

    Mono<VistaEstudiante> agregarInscripcion(InscripcionGeneric inscripcionGeneric, String estudianteID);

    Mono<VistaEstudiante> actualizarPromedio(String cursoID, Float promedio);

    Mono<VistaEstudiante> actualizarAvance(String cursoID, Float avance);

    Mono<VistaEstudiante> actualizarCalificacion(String cursoID, String tareaID, Integer calificacion);

    Mono<VistaEstudiante> actualizarTarea(String cursoID, String tareaID, EstadoTareaGeneric estadoTareaGeneric);

}
