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

    private final String cursoID;
    private final String temaID;
    private final String titulo;
    private final String fechaLimite;
    private final Float porcentaje;

    public CrearTarea(String cursoID, String temaID, String titulo, String fechaLimite, Float porcentaje) {
        this.cursoID = cursoID;
        this.temaID = temaID;
        this.titulo = titulo;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
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
