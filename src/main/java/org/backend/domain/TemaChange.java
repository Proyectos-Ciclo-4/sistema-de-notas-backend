package org.backend.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.entities.Tarea;
import org.backend.domain.events.PorcentajeDeTareaActualizado;
import org.backend.domain.events.TareaCreada;
import org.backend.domain.events.TemaCreado;
import org.backend.domain.valueobjects.Porcentaje;

import java.lang.reflect.InaccessibleObjectException;

public class TemaChange extends EventChange {
    public TemaChange(Tema tema) {
        apply((TemaCreado temaCreado) -> {
            tema.titulo = temaCreado.getTitulo();
            tema.orden = temaCreado.getOrden();
            tema.tareas = temaCreado.getTareas();
        });

        apply((TareaCreada tareaCreada) -> {
            Tarea tarea = new Tarea(
                    tareaCreada.getTareaID(),
                    tareaCreada.getTitulo(),
                    tareaCreada.getFechaLimite(),
                    tareaCreada.getPorcentaje()
            );

            tema.agregarTarea(tarea);
        });

        apply((PorcentajeDeTareaActualizado event) ->{
            var tarea = tema.encontrarTareaPorId(event.getTareaId())
                            .orElseThrow(() -> new InaccessibleObjectException("no hay tareas con ese id"));
            tarea.actualizarPorcentaje(new Porcentaje(event.getPorcentaje()));
        });

    }
}
