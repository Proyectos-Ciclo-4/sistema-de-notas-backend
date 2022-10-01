package org.backend.business.models.vistasmaterializadas;

import java.util.stream.Collectors;
import org.backend.business.models.vistasmaterializadas.generics.EstadoTareaGeneric;
import org.backend.business.models.vistasmaterializadas.generics.InscripcionGeneric;
import org.backend.domain.events.EstudianteCreado;

import java.util.HashSet;
import java.util.Set;

public class VistaEstudiante {
    private String _id;
    private String nombre;
    private Float promedio;
    private Float avance;

    private Set<InscripcionGeneric> inscripciones;

    public VistaEstudiante(String _id, String nombre, Float promedio, Float avance) {
        this._id = _id;
        this.nombre = nombre;
        this.promedio = promedio;
        this.avance = avance;
        this.inscripciones = new HashSet<>();
    }

    public VistaEstudiante(String _id, String nombre) {
        this._id = _id;
        this.nombre = nombre;
        this.promedio = Float.valueOf(0);
        this.avance = Float.valueOf(0);
        this.inscripciones = new HashSet<>();
    }

    public VistaEstudiante(String nombre) {
        this.nombre = nombre;
    }

    public static VistaEstudiante fromCreationEvent(EstudianteCreado estudianteCreado) {
        return new VistaEstudiante(
            estudianteCreado.getNombre().toString()
        );
    }


    public VistaEstudiante() {
    }

    public String get_id() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getPromedio() {
        return promedio;
    }

    public Float getAvance() {
        return avance;
    }

    public Set<InscripcionGeneric> getInscripciones() {
        return inscripciones;
    }

    public InscripcionGeneric encontrarInscripcion(String cursoID) {
        return inscripciones.stream().filter(inscripcionGeneric ->
            inscripcionGeneric.getCursoID().equals(cursoID)).findFirst().get();
    }

    public VistaEstudiante setAvance(){
        var nuevaInscripcion = inscripciones.stream().map(inscripcionGeneric -> {
            var totalTarea = inscripcionGeneric.getEstadosTarea().size();
            var tareasEntregada = inscripcionGeneric.getEstadosTarea().stream().filter(
                estadoTareaGeneric -> estadoTareaGeneric.getEstado().equals("Entregada")
            ).count();
            var promedio = (float) tareasEntregada/(float) totalTarea;
            inscripcionGeneric.setPromedio(promedio*100);
            return inscripcionGeneric;
        }).collect(Collectors.toSet());
        this.inscripciones = nuevaInscripcion;
        return this;
    }
}
