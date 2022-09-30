package org.unote_sockets.models.generics;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class InscripcionGeneric {
    private String cursoID;
    private String nombreCurso;
    private Float promedio;
    private Float avance;
    private LocalDate fechaInscripcion;

    private Set<EstadoTareaGeneric> estadosTarea;

    public InscripcionGeneric(String cursoID, Float promedio, Float avance, String nombreCurso, Set<EstadoTareaGeneric> estadosTarea) {
        this.cursoID = cursoID;
        this.promedio = promedio;
        this.avance = avance;
        this.nombreCurso = nombreCurso;
        this.fechaInscripcion = LocalDate.now();
        this.estadosTarea = estadosTarea;
    }

    public InscripcionGeneric(String cursoID, String nombreCurso, Set<EstadoTareaGeneric> estadosTarea) {
        this.cursoID = cursoID;
        this.promedio = (float) 0;
        this.avance = (float) 0;
        this.nombreCurso = nombreCurso;
        this.fechaInscripcion = LocalDate.now();

        this.estadosTarea = estadosTarea;
    }

    public InscripcionGeneric(String cursoID, String nombreCurso) {
        this.cursoID = cursoID;
        this.nombreCurso = nombreCurso;
        this.promedio = (float) 0;
        this.avance = (float) 0;
        this.fechaInscripcion = LocalDate.now();
        this.estadosTarea = new HashSet<>();
    }

    public InscripcionGeneric() {
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getCursoID() {
        return cursoID;
    }

    public void setCursoID(String cursoID) {
        this.cursoID = cursoID;
    }

    public Float getPromedio() {
        return promedio;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    public Float getAvance() {
        return avance;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setAvance(Float avance) {
        this.avance = avance;
    }

    public Set<EstadoTareaGeneric> getEstadosTarea() {
        return estadosTarea;
    }

    public void setEstadosTarea(Set<EstadoTareaGeneric> estadosTarea) {
        this.estadosTarea = estadosTarea;
    }

    public void agregarEstadoTarea(EstadoTareaGeneric estadoTareaGeneric) {
        this.estadosTarea.add(estadoTareaGeneric);
    }

    public EstadoTareaGeneric encontrarEstadoTarea(String tareaID) {
        return estadosTarea.stream().filter(estadoTareaGeneric ->
                estadoTareaGeneric.getTareaID().equals(tareaID)).findFirst().get();
    }

    public void eliminarEstadoTarea(String tareaID) {
        this.estadosTarea.removeIf(estadoTarea
                -> estadoTarea.getTareaID().equals(tareaID));

    }
}
