package org.backend.business.models.vistasmaterializadas;

import org.backend.business.models.vistasmaterializadas.generics.TemaGeneric;

import java.util.HashSet;
import java.util.Set;

public class VistaCurso {
    private String _id;
    private String titulo;

    private String profesorID;
    private Set<TemaGeneric> temas;

    private Set<String> inscritos;

    public VistaCurso(String _id, String titulo, String profesorID) {
        this._id = _id;
        this.titulo = titulo;
        this.profesorID = profesorID;
        this.temas = new HashSet<>();
        this.inscritos = new HashSet<>();
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

    public void setTemas(Set<TemaGeneric> temas) {
        this.temas = temas;
    }

    public void agregarTema(TemaGeneric temaGeneric) {
        this.temas.add(temaGeneric);
    }

    public boolean revisarInscripcion(String estudianteID) {
        return inscritos.contains(estudianteID);
    }
}
