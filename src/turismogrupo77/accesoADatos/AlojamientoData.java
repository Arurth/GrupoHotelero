/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import turismogrupo77.entidades.Alojamiento;
import turismogrupo77.entidades.Ciudad;
import turismogrupo77.entidades.Paquete;

/**
 *
 * @author Matias
 */
public class AlojamientoData {

    private Connection con = null;

    public AlojamientoData() {
        con = Conexion.getConexion();
    }

    public void guardarAlojamiento(Alojamiento alojamiento) {
        String sql = " INSERT INTO alojamiento (nombre, tipoAlojamiento, servicios, descServicios, importeDiario, ciudadDest, estado)" + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alojamiento.getNombre());
            ps.setString(2,alojamiento.getTipoAlojamiento());
            ps.setString(3, alojamiento.getServicios());
            ps.setString(4, alojamiento.getDescServicios());
            ps.setDouble(5, alojamiento.getImporteDiario());
            ps.setInt(6, alojamiento.getCiudadDest().getIdCiudad());
            ps.setBoolean(7, alojamiento.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setIdAlojamiento(rs.getInt(1));
                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "Se ha generado el Alojamiento" + rs.getInt(1));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public void elminarAlojamiento(int idAlojamiento) {
        String sql = "UPDATE  SET estado = 0 WHERE idAlojamiento = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlojamiento);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Alojamiento Eliminado");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alojamientp");
        }

    }

    public Alojamiento buscarAlojamiento(int idAlojamiento) {
           Ciudad ciudadencontrada = new Ciudad();
           CiudadData city = new CiudadData();
        //se fija primero si hay alguna ciudad con ese nombre
        String sql = "SELECT idAlojamiento, Nombre, tipoAlojamiento, servicios, descServicios, importeDiario, ciudadDest, estado FROM alojamiento WHERE idAlojamiento=?";
        Alojamiento alojamientoEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlojamiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               // JOptionPane.showMessageDialog(null, "Se ha encontrado Alojamiento con ese ID");
                alojamientoEncontrado = new Alojamiento();
                Ciudad ciudad = new Ciudad();
                alojamientoEncontrado.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamientoEncontrado.setNombre(rs.getString("Nombre"));
                alojamientoEncontrado.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                //alojamientoEncontrado.setFechaIn(rs.getDate("fechaIn").toLocalDate());
                //alojamientoEncontrado.setFechaOut(rs.getDate("fechaOut").toLocalDate());
                alojamientoEncontrado.setServicios(rs.getString("servicios"));
                alojamientoEncontrado.setDescServicios(rs.getString("descServicios"));
                alojamientoEncontrado.setImporteDiario(rs.getDouble("importeDiario"));
                ciudadencontrada = city.buscarCiudadID(rs.getInt("ciudadDest"));
                alojamientoEncontrado.setCiudadDest(ciudadencontrada);
                alojamientoEncontrado.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos Alojamiento " + ex.getMessage());
        }
        return alojamientoEncontrado;
    }

    public Alojamiento buscarAlojamientoIDCiudad(int idCiudad) {
        CiudadData city = new CiudadData();

        String sql = "SELECT idAlojamiento, nombre, tipoAlojamiento, importeDiario, servicios, descServicios, estado FROM alojamiento WHERE ciudadDest=?";
        Alojamiento alojamientoEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               // JOptionPane.showMessageDialog(null, "Se ha encontrado la ciudad con ese nombre");
                alojamientoEncontrado = new Alojamiento();
                alojamientoEncontrado.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamientoEncontrado.setServicios(rs.getString("servicios"));
                alojamientoEncontrado.setImporteDiario(rs.getDouble("importeDiario"));

                //busca la ciudad por ID y la agrega al Alojamiento
                Ciudad ciudadEncontrada = city.buscarCiudadID(idCiudad);
                alojamientoEncontrado.setCiudadDest(ciudadEncontrada);
                // ahi ya la agrego

                alojamientoEncontrado.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos " + ex.getMessage());
        }
        return alojamientoEncontrado;
    }

    public List<Alojamiento> listarAlojamientosIDciudad(int IdCiudad) {

        String sql = "SELECT idAlojamiento, nombre, tipoAlojamiento, importeDiario, servicios, descServicios, estado FROM alojamiento WHERE ciudadDest=? and estado=1";

        ArrayList<Alojamiento> alojamientos = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, IdCiudad);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamiento.setNombre(rs.getString("nombre"));
                alojamiento.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
                alojamiento.setImporteDiario(rs.getDouble("importeDiario"));

                alojamientos.add(alojamiento);

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alojamiento " + ex.getMessage());

        }
        return alojamientos;

    }
    
    public void modificarAlojamiento(Alojamiento alojamiento) {
        String sql = "UPDATE alojamiento SET idAlojamiento = ?, Nombre=?,"
                + "servicios= ?, descServicios=?,"
                + "importeDiario=?,ciudadDest=?,"
                + "tipoAlojamiento=?,"
                + "estado=? "
                + "WHERE idAlojamiento = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alojamiento.getIdAlojamiento());
            ps.setString(2,alojamiento.getNombre());
            ps.setString(3, alojamiento.getServicios());
            ps.setString(4, alojamiento.getDescServicios());
            ps.setDouble(5, alojamiento.getImporteDiario());
            ps.setInt(6, alojamiento.getCiudadDest().getIdCiudad());
            ps.setString(7, alojamiento.getTipoAlojamiento());
            ps.setBoolean(8, alojamiento.isEstado());
            ps.setInt(9,alojamiento.getIdAlojamiento());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setIdAlojamiento(rs.getInt(1));
                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "Se ha Modificado el Alojamiento" + rs.getInt(1));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

}
