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
        String sql = " INSERT INTO alojamiento (fechIn, fechaOut, servicio, importeDiario, ciudadDest)" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(alojamiento.getFechaIn()));
            ps.setDate(2, Date.valueOf(alojamiento.getFechaOut()));
            ps.setString(3, alojamiento.getServicio());
            ps.setDouble(4, alojamiento.getImporteDiario());
            ps.setString(5, alojamiento.getCiudadDest().getDestino());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alojamiento.setIdAlojamiento(rs.getInt(1));

                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "Se ha generado el pasaje" + rs.getInt(1));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public void elminarAlojamiento(int idAlojamiento) {
        String sql = "UPDATE  SET estado = 0 WHERE nombre = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlojamiento);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Ciudad Eliminada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla ciudad");
        }

    }


    public Alojamiento buscarAlojamiento(int idAlojamiento) {
        
        //se fija primero si hay alguna ciudad con ese nombre
        String sql = "SELECT idAlojamiento, fechaIn , fechaOut, servicio , importeDiario , ciudadDest ,estado  FROM Alojamiento WHERE idAlojamiento=?";
        Alojamiento alojamientoEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlojamiento);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JOptionPane.showMessageDialog(null, "Se ha encontrado la ciudad con ese nombre");
                alojamientoEncontrado = new Alojamiento();
                Ciudad ciudad= new Ciudad();
                alojamientoEncontrado.setIdAlojamiento(rs.getInt("idAlojamiento"));
                alojamientoEncontrado.setFechaIn(rs.getDate("fechaIn").toLocalDate());
                alojamientoEncontrado.setFechaOut(rs.getDate("fechaOut").toLocalDate());
                alojamientoEncontrado.setServicio(rs.getString("servicio"));
                alojamientoEncontrado.setImporteDiario(rs.getDouble("importeDiario"));
                ciudad.setIdCiudad(rs.getInt("ciudadDest"));
                alojamientoEncontrado.setCiudadDest(ciudad);
                alojamientoEncontrado.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos " + ex.getMessage());
        }
        return alojamientoEncontrado;
    }

}
