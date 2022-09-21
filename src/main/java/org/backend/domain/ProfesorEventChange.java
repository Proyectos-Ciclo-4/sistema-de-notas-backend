package org.backend.domain;

import co.com.sofka.domain.generic.EventChange;
import org.backend.domain.events.CursoAgregado;
import org.backend.domain.events.ProfesorCreado;

public class ProfesorEventChange extends EventChange {
    public ProfesorEventChange(Profesor profesor){
        apply((ProfesorCreado event) -> {
            profesor.nombre = event.getNombre();
        });

        apply((CursoAgregado event) -> {
            //Sacamos el id del curso agregado
            // se lo mandamos al modificador respectivo en el AR
        });
    }
}
