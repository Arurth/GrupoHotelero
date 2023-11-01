/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import turismogrupo77.entidades.Alojamiento;
import turismogrupo77.entidades.Ciudad;
import turismogrupo77.entidades.Pasaje;

/**
 *
 * @author Matias
 */
public class PasajeData {

    private Connection con = null;

    public PasajeData() {
        con = Conexion.getConexion();
    }

    public int guardarPasaje(Pasaje pasaje) {
        String sql = " INSERT INTO Pasaje (tipoTransporte, importe, ciudadOrigen, ciudadDest, estado)" + "VALUES(?,?,?,?,?)";
        int IDpasajeNuevo = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pasaje.getTipoTransporte());
            ps.setDouble(2, pasaje.getImporte());
            ps.setInt(3, pasaje.ciudadOrigen.getIdCiudad());
            ps.setInt(4, pasaje.ciudadDest.getIdCiudad());
            ps.setBoolean(5, pasaje.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pasaje.setIdPasaje(rs.getInt(1));

                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "Se ha generado el pasaje" + rs.getInt(1));
                IDpasajeNuevo = rs.getInt(1);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla pasaje" + ex.getMessage());
        }
        return IDpasajeNuevo;
    }

    public void elminarPasaje(int idPasaje) {
        String sql = "UPDATE  SET estado = 0 WHERE nombre = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPasaje);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Pasaje Eliminado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasajes");
        }

    }

    public int buscarPasajeRepetido(int Origen, int Destino, String Transporte){
        String sql = "SELECT idPasaje FROM pasaje WHERE ciudadOrigen = ? AND ciudadDest = ? AND tipoTransporte = ?";
        
        int idPasajeRepetido = 0;
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Origen);
            ps.setInt(2,Destino);
            ps.setString(3, Transporte);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                JOptionPane.showMessageDialog(null, "El pasaje ya Existe con esa configuracion");
                idPasajeRepetido = rs.getInt("idPasaje");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en el acceso a los pasajes "+ex.getMessage());
        }
        
        System.out.println("El Pasaje repetido es el numero "+idPasajeRepetido);
        return idPasajeRepetido;
        
    }
    
    public Pasaje buscarPasajeIDCiudad(int idCiudad) {

        
        String sql = "SELECT idPasaje, tipoTransporte, ciudadOrigen, ciudadDest, estado FROM pasaje WHERE ciudadOrigen=? OR ciudadDest=?";
        Pasaje pasajeEncontrado = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCiudad);
            ps.setInt(2, idCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //JOptionPane.showMessageDialog(null, "Se ha encontrado pasajes con ese nombre de ciudad");
                pasajeEncontrado = new Pasaje();
                Ciudad ciudadOR = new Ciudad();
                Ciudad ciudadDEST = new Ciudad();
                CiudadData city = new CiudadData();
                
                pasajeEncontrado.setIdPasaje(rs.getInt("idPasaje"));
                pasajeEncontrado.setTipoTransporte(rs.getString("tipoTransporte"));
                pasajeEncontrado.setImporte(rs.getDouble("importe"));
                ciudadOR = city.buscarCiudadID(rs.getInt("ciudadOrigen"));
                ciudadDEST = city.buscarCiudadID(rs.getInt("ciudadDest"));
                pasajeEncontrado.setCiudadOrigen(ciudadOR);
                pasajeEncontrado.setCiudadDest(ciudadDEST);
                pasajeEncontrado.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la tabla Pasaje " + ex.getMessage());
        }
        return pasajeEncontrado;
    }

    public List<Pasaje> listarPasajes(int idCiudad) {
        
        String sql = "SELECT idPasaje, tipoTransporte, ciudadOrigen, ciudadDest, importe, estado FROM pasaje WHERE ciudadOrigen=? OR ciudadDest=?";
        
        ArrayList<Pasaje> pasajesEncontrados = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCiudad);
            ps.setInt(2, idCiudad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasaje pasajeEncontrado = new Pasaje();
                Ciudad ciudadOR = new Ciudad();
                Ciudad ciudadDEST = new Ciudad();
                CiudadData city = new CiudadData();
                pasajeEncontrado.setIdPasaje(rs.getInt("idPasaje"));
                pasajeEncontrado.setTipoTransporte(rs.getString("tipoTransporte"));
                pasajeEncontrado.setImporte(rs.getDouble("importe"));
                ciudadOR = city.buscarCiudadID(rs.getInt("ciudadOrigen"));
                ciudadDEST = city.buscarCiudadID(rs.getInt("ciudadDest"));
                pasajeEncontrado.setCiudadOrigen(ciudadOR);
                pasajeEncontrado.setCiudadDest(ciudadDEST);
                pasajeEncontrado.setEstado(rs.getBoolean("estado"));

                pasajesEncontrados.add(pasajeEncontrado);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla pasaje " + ex.getMessage());

        }
        return pasajesEncontrados;

    }
    
    public List<Pasaje> listarPasajesOrigenDestino(int idCiudadOrigen, int idCiudadDestino) {
        
        String sql = "SELECT idPasaje, tipoTransporte, ciudadOrigen, ciudadDest, importe, estado FROM pasaje WHERE ciudadOrigen=? AND ciudadDest=?";
        
        ArrayList<Pasaje> pasajesEncontrados = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCiudadOrigen);
            ps.setInt(2, idCiudadDestino);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pasaje pasajeEncontrado = new Pasaje();
                Ciudad ciudadOR = new Ciudad();
                Ciudad ciudadDEST = new Ciudad();
                CiudadData city = new CiudadData();
                pasajeEncontrado.setIdPasaje(rs.getInt("idPasaje"));
                pasajeEncontrado.setTipoTransporte(rs.getString("tipoTransporte"));
                pasajeEncontrado.setImporte(rs.getDouble("importe"));
                ciudadOR = city.buscarCiudadID(rs.getInt("ciudadOrigen"));
                ciudadDEST = city.buscarCiudadID(rs.getInt("ciudadDest"));
                pasajeEncontrado.setCiudadOrigen(ciudadOR);
                pasajeEncontrado.setCiudadDest(ciudadDEST);
                pasajeEncontrado.setEstado(rs.getBoolean("estado"));

                pasajesEncontrados.add(pasajeEncontrado);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla pasaje " + ex.getMessage());

        }
        return pasajesEncontrados;

    }
    
    
    public Pasaje buscarPasaje(int idPasaje) {

        //se fija primero si hay alguna ciudad con ese nombre
        String sql = "SELECT idPasaje, tipoTransporte, importe, ciudadOrigen, estado  FROM Pasaje WHERE idPasaje=?";
        Pasaje pasajeEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPasaje);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               // JOptionPane.showMessageDialog(null, "Se ha encontrado un pasaje con ese nombre");
                pasajeEncontrado = new Pasaje();
                Ciudad ciudad = new Ciudad();
                pasajeEncontrado.setIdPasaje(rs.getInt("idPasaje"));
                pasajeEncontrado.setTipoTransporte(rs.getString("tipoTransporte"));
                pasajeEncontrado.setImporte(rs.getDouble("importe"));
                ciudad.setIdCiudad(rs.getInt("ciudadOrigen"));
                pasajeEncontrado.setCiudadOrigen(ciudad);
                pasajeEncontrado.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la tabla Pasaje " + ex.getMessage());
        }
        return pasajeEncontrado;
    }
    
    
    public void modificarPasaje(Pasaje pasaje) {
        String sql = "UPDATE pasaje SET importe = ?, tipoTransporte = ?, estado=? WHERE iDPasaje = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, pasaje.getImporte());
            ps.setString(2, pasaje.getTipoTransporte());
            ps.setBoolean(3, pasaje.isEstado());
            ps.setInt(4, pasaje.getIdPasaje());
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Pasaje "+pasaje.getIdPasaje()+" Modificado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Pasajes");
        }

    }

}
