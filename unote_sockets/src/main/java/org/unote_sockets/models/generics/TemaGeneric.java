package org.unote_sockets.models.generics;

import org.unote_sockets.models.vistasmaterializadas.VistaTarea;

import java.util.Set;

public class TemaGeneric {
    private String temaID;

    private String cursoID;
    private Integer orden;
    private String titulo;
    private Set<String> tareasID;

    private Set<VistaTarea> tareas;

    public Set<VistaTarea> getTareas() {
        return tareas;
    }

    public void setTareas(Set<VistaTarea> tareas) {
        this.tareas = tareas;
    }

    public TemaGeneric(String temaID, String cursoID, Integer orden, String titulo, Set<String> tareasID) {
        this.temaID = temaID;
        this.cursoID = cursoID;
        this.orden = orden;
        this.titulo = titulo;
        this.tareasID = tareasID;
    }

    public TemaGeneric() {
    }

    public String getTemaID() {
        return temaID;
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

    public Set<String> getTareasID() {
        return tareasID;
    }

    public void eliminarTareaEnArrays(String tareaID){
        this.tareasID.remove(tareaID);
        this.tareas.removeIf(vistaTarea -> vistaTarea.get_id().equals(tareaID));
    }

    public boolean hasTareas() {
        return !this.tareasID.isEmpty();
    }
}
