/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package turismogrupo77;

import turismogrupo77.accesoADatos.CiudadData;
import turismogrupo77.entidades.Ciudad;

/**
 *
 * @author Matias
 */
public class TurismoGrupo77 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        
   Ciudad ciudad = new Ciudad("Mendoza","Argentina","Mendoza",true);
   CiudadData ciudadData = new CiudadData();
    ciudadData.guardarCiudad(ciudad);
        System.out.println(ciudad);
    }
    
}
