package org.backend.business.models.vistasmaterializadas;

import java.util.HashSet;
import java.util.Set;

public class VistaProfesor extends VistaMaterializada {

    private String firebaseID;
    private String nombre;
    private Set<String> cursosIDS;

    // Al crear un profesor nuevo, su lista de cursos estará vacía por defecto,
    // así que no tenemos que pasarle un Set ya armado al constructor
    public VistaProfesor(String firebaseID, String nombre) {
        this.firebaseID = firebaseID;
        this.nombre = nombre;
        this.cursosIDS = new HashSet<>();
    }

    public String getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(String firebaseID) {
        this.firebaseID = firebaseID;
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
