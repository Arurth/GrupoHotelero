/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.entidades;

import java.time.LocalDate;


/**
 *
 * @author Matias
 */
public class Alojamiento {
    private int idAlojamiento;       
    private LocalDate fechaIn;
    private LocalDate fechaOut;
    private String servicio;
    private double importeDiario;
    private Ciudad ciudadDest;
    private boolean estado;

    public Alojamiento() {
    }

    public Alojamiento(int idAlojamiento, LocalDate fechaIn, LocalDate fechaOut, String servicio, double importe, Ciudad ciudadDest, boolean estado) {
        this.idAlojamiento = idAlojamiento;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.servicio = servicio;
        this.importeDiario = importe;
        this.ciudadDest = ciudadDest;
        this.estado = estado;
    }

    public Alojamiento(LocalDate fechaIn, LocalDate fechaOut, String servicio, double importe, Ciudad ciudadDest,boolean estado) {
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.servicio = servicio;
        this.importeDiario = importe;
        this.ciudadDest = ciudadDest;
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public LocalDate getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(LocalDate fechaIn) {
        this.fechaIn = fechaIn;
    }

    public LocalDate getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(LocalDate fechaOut) {
        this.fechaOut = fechaOut;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getImporteDiario() {
        return importeDiario;
    }

    public void setImporteDiario(double importeDiario) {
        this.importeDiario = importeDiario;
    }

    public Ciudad getCiudadDest() {
        return ciudadDest;
    }

    public void setCiudadDest(Ciudad ciudadDest) {
        this.ciudadDest = ciudadDest;
    }

    @Override
    public String toString() {
        return "Alojamiento{" + "idAlojamiento=" + idAlojamiento + '}';
    }
    
    
}
