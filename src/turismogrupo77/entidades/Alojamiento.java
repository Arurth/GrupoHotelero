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
    private String nombre;
    private String tipoAlojamiento;
    private LocalDate fechaIn;
    private LocalDate fechaOut;
    private String servicios;
    private String descServicios;
    private double importeDiario;
    private Ciudad ciudadDest;
    private boolean estado;
    

    public Alojamiento() {
    }
    
    public Alojamiento(int idAlojamiento, String nombre, String tipoAlojamiento, String servicios, String descServicios, double importeDiario, Ciudad ciudadDest, boolean estado) {
        this.idAlojamiento = idAlojamiento;
        this.servicios = servicios;
        this.descServicios =descServicios;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
        this.estado = estado;
        this.nombre = nombre;
        this.tipoAlojamiento = tipoAlojamiento;
    }

    public Alojamiento(int idAlojamiento, String nombre, String tipoAlojamiento, LocalDate fechaIn, LocalDate fechaOut, String servicios, String descServicios, double importeDiario, Ciudad ciudadDest, boolean estado) {
        this.idAlojamiento = idAlojamiento;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.servicios = servicios;
        this.descServicios =descServicios;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
        this.estado = estado;
        this.nombre = nombre;
        this.tipoAlojamiento = tipoAlojamiento;
    }

    public Alojamiento(String nombre, String tipoAlojamiento, String servicios, String descServicios, double importeDiario, Ciudad ciudadDest, boolean estado) {
        
        this.servicios = servicios;
        this.descServicios =descServicios;
        this.importeDiario = importeDiario;
        this.ciudadDest = ciudadDest;
        this.estado = estado;
        this.nombre = nombre;
        this.tipoAlojamiento = tipoAlojamiento;
    }

    public String getTipoAlojamiento() {
        return tipoAlojamiento;
    }

    public void setTipoAlojamiento(String tipoAlojamiento) {
        this.tipoAlojamiento = tipoAlojamiento;
    }

    public String getDescServicios() {
        return descServicios;
    }

    public void setDescServicios(String descServicios) {
        this.descServicios = descServicios;
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

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return idAlojamiento + " " + nombre + " " + tipoAlojamiento + " IMPORTE: " + importeDiario;
    }

    

    
    
    
}
