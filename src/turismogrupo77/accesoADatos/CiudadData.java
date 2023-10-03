/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.accesoADatos;

/**
 *
 * @author Fernando Oliva
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import turismogrupo77.entidades.Ciudad;

public class CiudadData {

    private Connection con = null;

    public CiudadData() {
        con = Conexion.getConexion();
    }
    String nombre;

    public Ciudad buscarCiudad(String nombre) {

        //se fija primero si hay alguna ciudad con ese nombre
        String sql = "SELECT idCiudad, nombre, provincia, pais, estado FROM ciudad WHERE nombre=?";
        Ciudad ciudadEncontrada = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Se ha encontrado la ciudad con ese nombre");
                ciudadEncontrada = new Ciudad();
                ciudadEncontrada.setIdCiudad(rs.getInt("idCiudad"));
                ciudadEncontrada.setNombre(rs.getString("nombre"));
                ciudadEncontrada.setPais(rs.getString("pais"));
                ciudadEncontrada.setProvincia(rs.getString("provincia"));
                ciudadEncontrada.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos " + ex.getMessage());
        }
        return ciudadEncontrada;
    }

    public void guardarCiudad(Ciudad ciudad) {
        

        if(buscarCiudad(ciudad.getNombre())!=null){
            JOptionPane.showMessageDialog(null, "La ciudad con el nombre "+ciudad.getNombre()+" ya Existe");
            return;
        }

        String sql = " INSERT INTO ciudad (nombre, pais, provincia, estado)" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ciudad.getNombre());
            ps.setString(2, ciudad.getPais());
            ps.setString(3, ciudad.getProvincia());;
            ps.setBoolean(4, ciudad.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ciudad.setIdCiudad(rs.getInt(1));

                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "La ciudad se Agrego Correctamente con el ID" + rs.getInt(1));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla ciudad " + ex.getMessage());
        }
    }

    public void modificarCiudad(Ciudad ciudad) {
        String sql = "UPDATE ciudad SET nombre=?, provincia=?, pais=?, estado=?"
                + " WHERE nombre = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ps.setString(2, ciudad.getProvincia());
            ps.setString(3, ciudad.getPais());
            ps.setBoolean(4, ciudad.isEstado());
            ps.setString(5, ciudad.getNombre());
            int exito = ps.executeUpdate();
            System.out.println(exito);

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alumno modificado");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    
    public void elminarCiudad(String nombre) {
        String sql = "UPDATE  SET estado = 0 WHERE nombre = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Ciudad Eliminada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla ciudad");
        }

    }
    
    public List<Ciudad> listarCiudades(String provincia) {
        String sql = "SELECT idCiudad, nombre, provincia, pais FROM ciudad WHERE estado = 1 AND provincia = ?";
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, provincia);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Ciudad ciudad= new Ciudad();
                ciudad.setIdCiudad(rs.getInt("idCiudad"));
                ciudad.setNombre(rs.getString("nombre"));
                ciudad.setProvincia(rs.getString("provincia"));
                ciudad.setPais(rs.getString("pais"));
                ciudad.setEstado(rs.getBoolean("estado"));

                ciudades.add(ciudad);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla ciudad "+ex.getMessage());

        }
        return ciudades;

    }
}
