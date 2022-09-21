package org.backend.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.backend.domain.identifiers.TareaID;
import org.backend.domain.valueobjects.FechaLimite;
import org.backend.domain.valueobjects.Porcentaje;
import org.backend.domain.valueobjects.Titulo;

import java.util.UUID;

public class TareaCreada extends DomainEvent {
    private TareaID tareaID;
    private Titulo titulo;
    private FechaLimite fechaLimite;
    private Porcentaje porcentaje;

    public TareaCreada() {
        super("unote.tareaCreada");
    }

    public TareaCreada(Titulo titulo, FechaLimite fechaLimite, Porcentaje porcentaje) {
        super("unote.tareaCreada");
        this.tareaID = TareaID.of(UUID.randomUUID().toString());
        this.titulo = titulo;
        this.fechaLimite = fechaLimite;
        this.porcentaje = porcentaje;
    }

    public TareaID getTareaID() {
        return tareaID;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public FechaLimite getFechaLimite() {
        return fechaLimite;
    }

    public Porcentaje getPorcentaje() {
        return porcentaje;
    }
}
