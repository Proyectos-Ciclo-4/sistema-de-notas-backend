package org.unote_sockets.domain;


import co.com.sofka.domain.generic.EventChange;
import org.unote_sockets.domain.commands.CrearTarea;
import org.unote_sockets.domain.events.TareaCreada;
import org.unote_sockets.domain.events.TemaCreado;

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
