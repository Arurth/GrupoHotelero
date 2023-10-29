/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package turismogrupo77.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
        String sql = " INSERT INTO Paquete (origen, destino, idAlojamiento, idPasaje, fechaSalida, fechaLlegada)" + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paquete.getOrigen().getNombre());
            ps.setString(2, paquete.getDestino().getNombre());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasaje());
            ps.setDate(5, Date.valueOf(paquete.getFechaSalida()));
            ps.setDate(6, Date.valueOf(paquete.getFechaLLegada()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                paquete.setIdPaquete(rs.getInt(1));

                //muestra que el insert fue exitoso y muestra el numero de ID (único)
                JOptionPane.showMessageDialog(null, "Se ha generado el paquete " + rs.getInt(1));
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete" + ex.getMessage());
        }
    }
    
    public List<Paquete> listarPaquetesCiudadDestino(Ciudad ciudad) {

        String sql = "SELECT idPaquete, origen, destino FROM paquete WHERE estado = 1 and destino = ?";

        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paquete paquete = new Paquete();
                Ciudad ciudadOrigen = new Ciudad();
                Ciudad ciudadDestino = new Ciudad();
                CiudadData city = new CiudadData();

                paquete.setIdPaquete(rs.getInt("idPaquete"));
                ciudadOrigen = city.buscarCiudad(rs.getString("origen"));
                ciudadDestino = city.buscarCiudad(rs.getString("destino"));
                paquete.setOrigen(ciudadOrigen);
                paquete.setDestino(ciudadDestino);

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;

    }

    public List<Paquete> listarPaquetesCiudadOrigen(Ciudad ciudad) {

        String sql = "SELECT idPaquete, origen, destino FROM paquete WHERE estado = 1 and origen = ?";

        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ciudad.getNombre());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paquete paquete = new Paquete();
                Ciudad ciudadOrigen = new Ciudad();
                Ciudad ciudadDestino = new Ciudad();
                CiudadData city = new CiudadData();

                paquete.setIdPaquete(rs.getInt("idPaquete"));
                ciudadOrigen = city.buscarCiudad(rs.getString("origen"));
                ciudadDestino = city.buscarCiudad(rs.getString("destino"));
                paquete.setOrigen(ciudadOrigen);
                paquete.setDestino(ciudadDestino);

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;

    }

    public Paquete buscarPaquete(int idPaquete) {

        //se fija primero si hay alguna ciudad con ese nombre
        String sql = "SELECT idPaquete, origen, destino, idAlojamiento, idPasaje, fechaSalida, fechaLlegada FROM Paquete WHERE idPaquete=?";
        Paquete paqueteEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaquete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ciudad origen = new Ciudad();
                Ciudad destino = new Ciudad();

                JOptionPane.showMessageDialog(null, "Se ha encontrado un Paquete con ese ID");
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
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos de Paquetes" + ex.getMessage());
        }
        return paqueteEncontrado;
    }

}
