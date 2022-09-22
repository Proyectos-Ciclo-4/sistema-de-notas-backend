package org.backend.business.models.vistasmaterializadas.generics;

import org.backend.domain.identifiers.TareaID;

import java.util.Set;

public class TemaGeneric {
    private Integer orden;
    private String titulo;
    private Set<TareaID> tareasID;

    public TemaGeneric(Integer orden, String titulo, Set<TareaID> tareasID) {
        this.orden = orden;
        this.titulo = titulo;
        this.tareasID = tareasID;
    }

    public Integer getOrden() {
        return orden;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<TareaID> getTareasID() {
        return tareasID;
    }
}
