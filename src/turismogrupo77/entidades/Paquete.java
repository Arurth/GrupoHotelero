/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.entidades;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Matias
 */
public class Paquete {
    private int idPaquete;
    private Ciudad origen;
    private Ciudad destino;
    private Alojamiento alojamiento;
    private Pasaje pasaje;
    private LocalDate fechaSalida;
    private LocalDate fechaLLegada;
    private boolean estado;
    private int cantPersonas;
    private double importe;
    private String cliente;
    private String temporada;
    private int cantDias;

    public Paquete() {
    }

    public Paquete(int idPaquete, Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje, LocalDate fechaSalida, LocalDate fechaLLegada, boolean estado, int cantPersonas, double importe, String cliente, String temporada, int cantDias) {
        this.idPaquete = idPaquete;
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
        this.fechaSalida = fechaSalida;
        this.fechaLLegada = fechaLLegada;
        this.estado = estado;
        this.cantPersonas = cantPersonas;
        this.importe = importe;
        this.cliente = cliente;
        this.temporada = temporada;
        this.cantDias = cantDias;
    }

    public Paquete(Ciudad origen, Ciudad destino, Alojamiento alojamiento, Pasaje pasaje, LocalDate fechaSalida, LocalDate fechaLLegada, boolean estado, int cantPersonas, double importe, String cliente, int cantDias) {
        this.origen = origen;
        this.destino = destino;
        this.alojamiento = alojamiento;
        this.pasaje = pasaje;
        this.fechaSalida = fechaSalida;
        this.fechaLLegada = fechaLLegada;
        this.estado = estado;
        this.cantPersonas = cantPersonas;
        this.importe = importe;
        this.cliente = cliente;
        this.temporada = temporada;
        this.cantDias = cantDias;
    }

    public int getCantDias() {
        return cantDias;
    }

    public void setCantDias(int cantDias) {
        this.cantDias = cantDias;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

   

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaLLegada() {
        return fechaLLegada;
    }

    public void setFechaLLegada(LocalDate fechaLLegada) {
        this.fechaLLegada = fechaLLegada;
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(int idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaquete=" + idPaquete + ", origen=" + origen + ", destino=" + destino + '}';
    }
    
}
