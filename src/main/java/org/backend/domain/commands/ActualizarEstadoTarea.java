package org.backend.domain.commands;

import co.com.sofka.domain.generic.Command;

import java.io.File;
import java.util.Date;

// Comando empleado para:
// 1) Enviar una tarea, acción del estudiante.
// 2) Calificar una tarea, acción del profesor.
public class ActualizarEstadoTarea extends Command {
    /* Representación JSON:
        {
            calificacion: Calificación entregada por el profesor. null si es un POST hecho por estudiante.
            date: Fecha en la cual el estudiante entrega la tarea. null si es un POST hecho por profesor.
            file: Archivo enviado por el estudiante al entregar la tarea. null si es un POST hecho por el profesor.
            entregado: True al enviar el estudiante la tarea. null si es un POST hecho por el profesor.
        }
     */
    private Integer calificacion;
    private Date fechaEntregada;
    private File archivo;
    private Boolean entregado;

    public ActualizarEstadoTarea(Integer calificacion, Date fechaEntregada, File archivo, Boolean entregado) {
        this.calificacion = calificacion;
        this.fechaEntregada = fechaEntregada;
        this.archivo = archivo;
        this.entregado = entregado;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public Date getFechaEntregada() {
        return fechaEntregada;
    }

    public File getArchivo() {
        return archivo;
    }

    public Boolean getEntregado() {
        return entregado;
    }
}
