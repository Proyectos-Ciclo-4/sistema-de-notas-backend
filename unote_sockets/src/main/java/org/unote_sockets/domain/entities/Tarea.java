package org.unote_sockets.domain.entities;

import co.com.sofka.domain.generic.Entity;
import org.unote_sockets.domain.identifiers.TareaID;
import org.unote_sockets.domain.valueobjects.FechaLimite;
import org.unote_sockets.domain.valueobjects.Porcentaje;
import org.unote_sockets.domain.valueobjects.Titulo;

import java.util.Objects;

public class Tarea extends Entity<TareaID> {

    private Titulo titulo;
    private FechaLimite fechaLimite;
    private Porcentaje porcentaje;

    public Tarea(TareaID entityId, Titulo titulo, FechaLimite fechaLimite, Porcentaje porcentaje) {
        super(entityId);
        this.titulo = titulo;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
    }

    // Getters sintácticos

    public Titulo Titulo() {
        return titulo;
    }

    public FechaLimite FechaLimite() {
        return fechaLimite;
    }

    public Porcentaje Porcentaje() {
        return porcentaje;
    }

    // Comportamientos

    public void actualizarPorcentaje(Porcentaje porcentaje){
        this.porcentaje = Objects.requireNonNull(porcentaje);
    }

    //public void actualizar
}
