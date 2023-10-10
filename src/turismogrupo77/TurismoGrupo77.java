/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package turismogrupo77;

import java.sql.Date;
import java.time.LocalDate;
import turismogrupo77.accesoADatos.AlojamientoData;
import turismogrupo77.accesoADatos.CiudadData;
import turismogrupo77.accesoADatos.PaqueteData;
import turismogrupo77.accesoADatos.PasajeData;
import turismogrupo77.entidades.Alojamiento;
import turismogrupo77.entidades.Ciudad;
import turismogrupo77.entidades.Paquete;
import turismogrupo77.entidades.Pasaje;

/**
 *
 * @author Matias
 */
public class TurismoGrupo77 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       
   Ciudad ciudad = new Ciudad("Misiones","Argentina","Mendoza",true);
   CiudadData ciudadData = new CiudadData();
   
   Ciudad ciudad1 = new Ciudad("Cordoba","Argentina","Buenos Aires",true);
   
   Ciudad c1 = ciudadData.buscarCiudad("Cordoba");
   Ciudad c2 = ciudadData.buscarCiudad("Rosario");
   
   Alojamiento alojamiento = new Alojamiento(LocalDate.parse("1997-07-09"),LocalDate.parse("1997-10-09"),"colectivo",1990.09,c1,true);
   AlojamientoData alojamientoData = new AlojamientoData();
   alojamientoData.guardarAlojamiento(alojamiento);
        
       
   Pasaje pasaje = new Pasaje("colectivo",1990.09,c1,c2,true);
   PasajeData pasajeData= new PasajeData();
   pasajeData.guardarPasaje(pasaje);
   
   
   Paquete paquete= new Paquete(c2,c1,alojamiento,pasaje);
   PaqueteData paqueteData = new PaqueteData();
   paqueteData.guardarPaquete(paquete);
        
        
        
    }
    
    }
    
