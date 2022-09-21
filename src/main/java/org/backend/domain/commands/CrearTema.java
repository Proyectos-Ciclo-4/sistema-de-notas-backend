package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

import java.util.Set;

public class CrearTema extends Command {
    private final Integer orden;
    private final String titulo;

    private final Set<CrearTarea> tareas;

    public CrearTema(Integer orden, String titulo, Set<CrearTarea> tareas) {
        this.orden = orden;
        this.titulo = titulo;
        this.tareas = tareas;
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
