package org.unote_sockets.models.vistasmaterializadas;

import java.util.HashSet;
import java.util.Set;

public class VistaProfesor {

    private String _id;
    private String nombre;
    private Set<String> cursosIDS;

    // Al crear un profesor nuevo, su lista de cursos estará vacía por defecto,
    // así que no tenemos que pasarle un Set ya armado al constructor
    public VistaProfesor(String _id, String nombre) {
        this._id = _id;
        this.nombre = nombre;
        this.cursosIDS = new HashSet<>();
    }

    public VistaProfesor() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<String> getCursosIDS() {
        return cursosIDS;
    }

    public void setCursosIDS(Set<String> cursosIDS) {
        this.cursosIDS = cursosIDS;
    }
}
