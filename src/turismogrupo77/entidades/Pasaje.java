/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.entidades;

/**
 *
 * @author Matias
 */
public class Pasaje {
    private int idPasaje;
    private String tipoTransporte;
    private double importe;
    private Ciudad ciudad;
    private boolean estado;

    public Pasaje() {
    }

    public Pasaje(int idPasaje, String tipoTransporte, double importe, Ciudad ciudad, boolean estado) {
        this.idPasaje = idPasaje;
        this.tipoTransporte = tipoTransporte;
        this.importe = importe;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public Pasaje(String tipoTransporte, double importe, Ciudad ciudad, boolean estado) {
        this.tipoTransporte = tipoTransporte;
        this.importe = importe;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getIdPasaje() {
        return idPasaje;
    }

    public void setIdPasaje(int idPasaje) {
        this.idPasaje = idPasaje;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pasaje{" + "idPasaje=" + idPasaje + ", tipoTransporte=" + tipoTransporte + ", ciudad=" + ciudad + '}';
    }
    
    
}
