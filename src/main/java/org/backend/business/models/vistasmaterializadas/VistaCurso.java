package org.backend.business.models.vistasmaterializadas;

import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;

import java.util.Set;

public class VistaCurso {
    private String _id;
    private String titulo;

    private String profesorID;
    private Set<TemaGeneric> temas;

    public VistaCurso(String _id, String titulo, String profesorID, Set<TemaGeneric> temas) {
        this._id = _id;
        this.titulo = titulo;
        this.profesorID = profesorID;
        this.temas = temas;
    }

    public VistaCurso() {
    }

    public String get_id() {
        return _id;
    }

    public String getProfesorID() {
        return profesorID;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<TemaGeneric> getTemas() {
        return temas;
    }
}
