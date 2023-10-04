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
   Ciudad ciudad1 = new Ciudad("Buenos Aires","Argentina","Buenos Aires",true);
   ciudadData.guardarCiudad(ciudad);
   Alojamiento alojamiento = new Alojamiento("1997/07/09","1997/10/09","colectivo",1990.09,ciudad);
   
        
        
        Paquete paquete= new Paquete(ciudad,ciudad1,);
        
        
        
    }
    
}
