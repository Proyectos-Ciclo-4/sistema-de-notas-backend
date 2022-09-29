package org.backend.business.models.vistasmaterializadas.generics;

import org.backend.business.models.vistasmaterializadas.VistaTarea;
import org.backend.domain.identifiers.TareaID;

import java.util.Set;
import java.util.stream.Collectors;

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
}
