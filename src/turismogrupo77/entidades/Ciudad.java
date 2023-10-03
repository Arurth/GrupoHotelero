/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.entidades;

/**
 *
 * @author Matias
 */
public class Ciudad {

    private int idCiudad;
    private String nombre;
    private String pais ;
    private String provincia;
    private boolean estado;

    public Ciudad() {
    }

    public Ciudad(int idCiudad, String nombre, String pais, String provincia, boolean estado) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.pais = pais;
        this.provincia = provincia;
        this.estado = estado;
    }

    public Ciudad(String nombre, String pais, String provincia, boolean estado) {
        this.nombre = nombre;
        this.pais = pais;
        this.provincia = provincia;
        this.estado = estado;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "idCiudad=" + idCiudad + ", nombre=" + nombre + ", pais=" + pais + ", provincia=" + provincia + '}';
    }
    
    

}
