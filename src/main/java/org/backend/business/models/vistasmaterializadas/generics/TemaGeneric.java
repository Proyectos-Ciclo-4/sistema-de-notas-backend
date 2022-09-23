package org.backend.business.models.vistasmaterializadas.generics;

import org.backend.domain.identifiers.TareaID;

import java.util.Set;

public class TemaGeneric {
    private String temaID;

    private String cursoID;
    private Integer orden;
    private String titulo;
    private Set<String> tareasID;

    public TemaGeneric(String temaID, String cursoID, Integer orden, String titulo, Set<String> tareasID) {
        this.temaID = temaID;
        this.cursoID = cursoID;
        this.orden = orden;
        this.titulo = titulo;
        this.tareasID = tareasID;
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
}
