/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.entidades;

import java.util.Date;

/**
 *
 * @author Matias
 */
public class Alojamiento {
    private int idAlojamiento;
    private Date fechaIn;
    private Date fechaOut;
    private String servicio;
    private double importe;
    private Ciudad ciudadDest;

    public Alojamiento() {
    }

    public Alojamiento(int idAlojamiento, Date fechaIn, Date fechaOut, String servicio, double importe, Ciudad ciudadDest) {
        this.idAlojamiento = idAlojamiento;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.servicio = servicio;
        this.importe = importe;
        this.ciudadDest = ciudadDest;
    }

    public Alojamiento(Date fechaIn, Date fechaOut, String servicio, double importe, Ciudad ciudadDest) {
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.servicio = servicio;
        this.importe = importe;
        this.ciudadDest = ciudadDest;
    }

    public int getIdAlojamiento() {
        return idAlojamiento;
    }

    public void setIdAlojamiento(int idAlojamiento) {
        this.idAlojamiento = idAlojamiento;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
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
