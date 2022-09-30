package org.unote_sockets.domain.commands;

import co.com.sofka.domain.generic.Command;

import java.util.Set;

// Comando empleado para crear un tema nueva en la base de datos, por parte de un profesor.
// Empleado en el comando CrearCurso.
// Utiliza al comando CrearTarea.
public class CrearTema extends Command {
    /* Representación JSON:
        {
            cursoID: Id del curso al cual pertenece.
            orden: Posición que ocupa en el curso.
            titulo: Título de la tema.
            tareas: Arreglo de comandos CrearTarea. Incluye las tareas del tema. Puede estar vacío.
        }
     */
    private String cursoID;
    private Integer orden;
    private String titulo;
    private Set<CrearTarea> tareas;

    public CrearTema(String cursoID, Integer orden, String titulo, Set<CrearTarea> tareas) {
        this.cursoID = cursoID;
        this.orden = orden;
        this.titulo = titulo;
        this.tareas = tareas;
    }

    public CrearTema(Integer orden, String titulo, Set<CrearTarea> tareas) {
        this.orden = orden;
        this.titulo = titulo;
        this.tareas = tareas;
    }

    public CrearTema() {
    }

    public void setCursoID(String cursoID) {
        this.cursoID = cursoID;
    }

    public String getCursoID() {
        return cursoID;
    }

    public Integer getOrden() {
        return orden;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<CrearTarea> getTareas() {
        return tareas;
    }
}
