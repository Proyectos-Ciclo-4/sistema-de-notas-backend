package org.backend.business.models.vistasmaterializadas;

import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;

import java.util.Set;

public class VistaCurso extends VistaMaterializada{
    private String cursoID;
    private String titulo;

    private String profesorID;
    private Set<TemaGeneric> temas;

    public VistaCurso(String cursoID, String titulo, String profesorID, Set<TemaGeneric> temas) {
        this.cursoID = cursoID;
        this.titulo = titulo;
        this.profesorID = profesorID;
        this.temas = temas;
    }

    public String getCursoID() {
        return cursoID;
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
