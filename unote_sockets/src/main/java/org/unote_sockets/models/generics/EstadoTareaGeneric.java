package org.unote_sockets.models.generics;

import java.time.LocalDate;

public class EstadoTareaGeneric {

    private String tareaID;
    private String titulo;
    private String temaID;

    private String temaNombre;

    private Integer calificacion;

    // private String fechaLimite;


    // private String fechaEntregado;
    private String archivoURL;
    private String estado;

    private Integer orden;

    private String retroalimentacion;

    private String tipo;

    public void setNumero(Integer numero) {
        this.orden = numero;
    }
// Constructor para crear objeto EstadoTareaGeneric por primera vez


    public EstadoTareaGeneric(String tareaID, String titulo, String temaID, String temaNombre, Integer orden) {
        this.tareaID = tareaID;
        this.titulo = titulo;
        this.temaID = temaID;
        this.temaNombre = temaNombre;
        this.calificacion = 0;
        // this.fechaLimite = fechaLimite;
        // this.fechaEntregado = null;
        this.archivoURL = "";
        this.estado = "Sin entregar";
        this.orden = orden;
        this.retroalimentacion = "";
    }

    public EstadoTareaGeneric(String tareaID, String archivoURL) {
        //this.fechaEntregado = LocalDate.now().toString();
        this.archivoURL = archivoURL;
        this.estado = "Entregada";
        this.retroalimentacion = "";
    }

    public EstadoTareaGeneric() {
    }

    public void setTareaID(String tareaID) {
        this.tareaID = tareaID;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTareaID() {
        return tareaID;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTemaID() {
        return temaID;
    }

    public void setTemaID(String temaID) {
        this.temaID = temaID;
    }

    public String getTemaNombre() {
        return temaNombre;
    }

    public void setTemaNombre(String temaNombre) {
        this.temaNombre = temaNombre;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    /*
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

     */

    /*
    public String getFechaEntregado() {
        return fechaEntregado;
    }

     */

    public String getTitulo() {
        return titulo;
    }

    /*
    public String getFechaLimite() {
        return fechaLimite;
    }
     */


    /*
    public void setFechaEntregado(String fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

     */

    public String getArchivoURL() {
        return archivoURL;
    }

    public void setArchivoURL(String archivoURL) {
        this.archivoURL = archivoURL;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getOrden() {
        return orden;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public EstadoTareaGeneric actualizarTarea(String archivoURL) {
        this.setArchivoURL(archivoURL);
        // this.setFechaEntregado(LocalDate.now().toString());
        this.setEstado("Entregada");

        return this;
    }

    public EstadoTareaGeneric calificarTarea(Integer calificacion, String retroalimentacion) {
        this.setEstado("Calificado");
        this.setCalificacion(calificacion);
        this.setRetroalimentacion(retroalimentacion);

        return this;
    }

}
