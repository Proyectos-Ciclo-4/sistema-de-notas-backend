package org.backend.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.commands.CrearTarea;
import org.backend.domain.entities.Tarea;
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
            CrearTarea tarea = new CrearTarea(
                    tareaCreada.getCursoID(),
                    tareaCreada.getTareaID(),
                    tareaCreada.getTitulo(),
                    tareaCreada.getDescripcion(),
                    tareaCreada.getOrden(),
                    tareaCreada.getFechaLimite(),
                    tareaCreada.getPorcentaje()

            );

            tema.agregarTarea(tarea);
        });

    }
}
