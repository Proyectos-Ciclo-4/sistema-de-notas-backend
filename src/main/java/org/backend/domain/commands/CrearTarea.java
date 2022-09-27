package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;


// Comando empleado para crear una tarea nueva en la base de datos, por parte de un profesor.
// Empleado en el comando CrearTema
public class CrearTarea extends Command {
    /* Representación JSON:
        {
            cursoID: Id del curso al cual pertenece.
            temaID: Id del tema al cual pertenece.
            titulo: Título de la tarea.
            fechaLimite: Fecha en formato DD/MM/YYYY.
            porcentaje: Porcentaje de la tarea con relación al curso.
        }
     */

    private String cursoID;
    private String temaID;

    private String temaNombre;
    private String titulo;

    private String descripcion;

    private Integer orden;

    private String fechaLimite;
    private Float porcentaje;

    public CrearTarea(String cursoID, String temaID, String titulo, String descripcion, Integer orden, String fechaLimite, Float porcentaje) {
        this.cursoID = cursoID;
        this.temaID = temaID;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.orden = orden;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
    }

    public CrearTarea(String titulo, String fechaLimite, Float porcentaje) {
        this.titulo = titulo;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
    }

    public CrearTarea() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTemaNombre() {
        return temaNombre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setCursoID(String cursoID) {
        this.cursoID = cursoID;
    }

    public void setTemaID(String temaID) {
        this.temaID = temaID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public String getTemaID() {
        return temaID;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }
}
