package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.backend.domain.identifiers.CursoID;
import org.backend.domain.identifiers.ProfesorID;
import org.backend.domain.identifiers.TemaID;
import org.backend.domain.valueobjects.Titulo;

import java.util.Set;

// Comando empleado para crear un curso nuevo en la base de datos, por parte de un profesor.
// Utiliza al comando CrearTema.
public class CrearCurso extends Command {
    /* Representación JSON:
        {
            titulo: Título de la tema.
            profesorID: ID de firebase del profesor que creó el curso
            temas: Arreglo de comandos CrearTema. Incluye los temas del curso, que a su vez
                    pueden incluir tareas. Puede estar vacío.
        }
     */
    private final Titulo titulo;
    private final ProfesorID profesorID;
    private final Set<CrearTema> temas;


    public CrearCurso(Titulo titulo, ProfesorID profesorID, Set<CrearTema> temas) {
        this.titulo = titulo;
        this.profesorID = profesorID;
        this.temas = temas;
    }


    public Titulo getTitulo() {
        return titulo;
    }

    public ProfesorID getProfesorID() {
        return profesorID;
    }

    public Set<CrearTema> getTemas() {
        return temas;
    }
}
