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
import turismogrupo77.entidades.Pasaje;

/**
 *
 * @author Matias
 */
public class PaqueteData {

    private Connection con = null;

    public PaqueteData() {
        con = Conexion.getConexion();
    }

    public void guardarPaquete(Paquete paquete) {
        String sql = " INSERT INTO Paquete (origen, destino, idAlojamiento, idPasaje)" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paquete.getOrigen().getNombre());
            ps.setString(2, paquete.getDestino().getNombre());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasaje());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paquete.setIdPaquete(rs.getInt(1));

                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "Se ha generado el paquete" + rs.getInt(1));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alojamiento " + ex.getMessage());
        }
    }

    public void elminarAlojamiento(int idPaquete) {
        String sql = "UPDATE  SET estado = 0 WHERE nombre = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaquete);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Ciudad Eliminada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla ciudad");
        }

    }

    public Paquete buscarPaquete(int idPaquete) {

        //se fija primero si hay alguna ciudad con ese nombre
        String sql = "SELECT idPaquete,origen, destino, idAlojamiento, idPasaje  FROM Paquete WHERE idPaquete=?";
        Paquete paqueteEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaquete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ciudad origen= new Ciudad();
                Ciudad destino= new Ciudad();
                
                JOptionPane.showMessageDialog(null, "Se ha encontrado la ciudad con ese nombre");
                paqueteEncontrado = new Paquete();
                Alojamiento alojamiento = new Alojamiento();
                Pasaje pasaje = new Pasaje();
                
                paqueteEncontrado.setIdPaquete(rs.getInt("idPaquete"));
                origen.setNombre(rs.getString("origen"));
                paqueteEncontrado.setOrigen(origen);
                destino.setNombre(rs.getString("destino"));
                paqueteEncontrado.setDestino(destino);
                
                alojamiento.setIdAlojamiento(rs.getInt("idAlojamiento"));
                paqueteEncontrado.setAlojamiento(alojamiento);
                pasaje.setIdPasaje(rs.getInt("idPasaje"));
                paqueteEncontrado.setPasaje(pasaje);

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos " + ex.getMessage());
        }
        return paqueteEncontrado;
    }

}
