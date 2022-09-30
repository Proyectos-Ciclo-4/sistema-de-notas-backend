package org.unote_sockets.models.vistasmaterializadas;


import org.unote_sockets.models.generics.TemaGeneric;

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

    public Set<String> getInscritos() {
        return inscritos;
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

    public TemaGeneric encontrarTema(String temaID) {
        return this.temas.stream().filter(temaGeneric ->
                temaGeneric.getTemaID().equals(temaID)).findFirst().get();
    }

    public VistaCurso eliminarTemaPorID(String temaID) {
        this.temas.removeIf(temaGeneric -> temaGeneric.getTemaID().equals(temaID));

        System.out.println(this.temas.size());

        return this;
    }


}
