package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

// Comando empleado para crear un Estudiante nuevo en la base de datos.
public class CrearEstudiante extends Command {
    /* Representación JSON:
        {
            estudianteID: ID del usuario en Firebase.
            nombre: Nombre del estudiante
        }
     */

    private String estudianteID;
    private String nombre;

    // No es necesario crear una propiedad para el Map<curso_id, inscripcion> porque al crear el estudiante,
    // esa propiedad no existe. Sólo se genera al inscribirse en un curso.

    public CrearEstudiante(String estudianteID, String nombre) {
        this.estudianteID = estudianteID;
        this.nombre = nombre;
    }

    public String getEstudianteID() {
        return estudianteID;
    }

    public String getNombre() {
        return nombre;
    }
}
