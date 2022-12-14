package org.backend.business.gateways;

import org.backend.business.models.vistasmaterializadas.VistaCurso;
import org.backend.business.models.vistasmaterializadas.VistaEstudiante;
import org.backend.business.models.vistasmaterializadas.VistaProfesor;
import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;
import org.backend.domain.valueobjects.EstadoTarea;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ViewRepository {

    // Vista para profesor

    Mono<VistaProfesor> crearProfesor(VistaProfesor vistaProfesor);

    Flux<VistaProfesor> encontrarTodosProfesores();

    Mono<VistaProfesor> encontrarProfesorPorID(String profesorID);

    void agregarCursoIDaProfesor(String profesorID, String cursoID);

    // Vista para estudiante

    Mono<VistaEstudiante> crearEstudiante(VistaEstudiante vistaEstudiante);

    Flux<VistaEstudiante> encontrarTodosEstudiantes();

    Mono<VistaEstudiante> encontrarEstudiantePorID(String estudianteID);

    Flux<VistaEstudiante> listarEstudiantesEnCurso(String cursoID);

    Mono<VistaEstudiante> agregarInscripcion(InscripcionGeneric inscripcionGeneric, String estudianteID);

    void agregarTareaAInscripcion(String cursoID, EstadoTareaGeneric estadoTareaGeneric);

    Mono<EstadoTareaGeneric> entregarTarea(String estudianteID, String cursoID, String tareaID, String URLArchivo);

    Mono<VistaEstudiante> actualizarPromedio(String cursoID, Float promedio);

    Mono<VistaEstudiante> actualizarAvance(String cursoID, Float avance);

    Mono<VistaEstudiante> actualizarCalificacion(String cursoID, String tareaID, Integer calificacion);

    Mono<VistaEstudiante> actualizarTarea(String cursoID, String tareaID, EstadoTareaGeneric estadoTareaGeneric);

    //Vista para curso
    Mono<VistaCurso> encontrarCursoPorId(String cursoID);

    Flux<VistaCurso> encontrarCursoPorRegex(String regex);
    Flux<VistaCurso> listarCursos();
    Mono<VistaCurso> crearCurso(VistaCurso curso);
    Mono<TemaGeneric> agregarTema(TemaGeneric nuevoTema);
    Mono<VistaCurso> agregarTareaID(String tareaID);

    void agregarInscritoACurso(String estudianteID, String cursoID);

    //Vista para tarea
    Mono<VistaTarea> encontrarTareaPorID(String tareaID);
    Flux<VistaTarea> listarTareasPorCurso(String cursoID);

    Mono<VistaTarea> crearTarea(VistaTarea vistaTarea);

    Mono<VistaEstudiante> calificarTarea(String estudianteID, String cursoID, String tareaID, Integer calificacion, String retroalimentacion);

    Mono<TemaGeneric> encontrarTema(String temaID);

    void eliminarTareaDeEstudiante(String cursoID, String tareaID, String temaID);

    void eliminarTareaDeCurso(String cursoID, String tareaID, String temaID);

    void eliminarVistaTarea(String tareaID);

    void eliminarTareasPorTema(String cursoID, String temaID);

    void eliminarTema(String cursoID, String temaID);



}
