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
        String sql = " INSERT INTO Paquete (origen, destino, idAlojamiento, idPasaje, fechaSalida, fechaLlegada, estado, cantPersonas, Importe, Cliente)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paquete.getOrigen().getNombre());
            ps.setString(2, paquete.getDestino().getNombre());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasaje());
            ps.setDate(5, Date.valueOf(paquete.getFechaSalida()));
            ps.setDate(6, Date.valueOf(paquete.getFechaLLegada()));
            ps.setBoolean(7, paquete.isEstado());
            ps.setInt(8, paquete.getCantPersonas());
            ps.setDouble(9,paquete.getImporte());
            ps.setString(10, paquete.getCliente());
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
    
    public List<Paquete> listarPaquetesNombreCliente(String cliente) {

        String sql = "SELECT idPaquete, origen, destino, cliente FROM paquete WHERE estado = 1 and cliente = ?";

        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente);
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
                paquete.setCliente(rs.getString("Cliente"));

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;

    }
    
     public List<Paquete> listarPaquetesCiudadDestino(Ciudad ciudad) {

        String sql = "SELECT idPaquete, origen, destino, Cliente FROM paquete WHERE estado = 1 and destino = ?";

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
                paquete.setCliente(rs.getString("Cliente"));

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;

    }
     
         public List<Paquete> listarPaquetesActivos(boolean estado) {

        String sql = "SELECT idPaquete, origen, destino, Cliente FROM paquete WHERE estado = ?";

        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, estado);
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
                paquete.setCliente(rs.getString("Cliente"));

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;
    }
    
    public List<Paquete> listarPaquetesID(int idPaquete) {

        String sql = "SELECT idPaquete, origen, destino, Cliente FROM paquete WHERE estado = 1 and idPaquete = ?";

        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaquete);
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
                paquete.setCliente(rs.getString("Cliente"));

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;
    }
    
    public List<Paquete> listarPaquetesCiudadDestino(int idPaquete) {

        String sql = "SELECT idPaquete, origen, destino, Cliente FROM paquete WHERE estado = 1 and idPaquete = ?";

        ArrayList<Paquete> paquetes = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaquete);
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
                paquete.setCliente(rs.getString("Cliente"));

                paquetes.add(paquete);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete " + ex.getMessage());

        }
        return paquetes;

    }

    public List<Paquete> listarPaquetesCiudadOrigen(Ciudad ciudad) {

        String sql = "SELECT idPaquete, origen, destino, cliente FROM paquete WHERE estado = 1 and origen = ?";

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
                paquete.setCliente(rs.getString("Cliente"));

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
        String sql = "SELECT idPaquete, origen, destino, idAlojamiento, idPasaje, fechaSalida, fechaLlegada, estado, cantPersonas, Importe, Cliente FROM Paquete WHERE idPaquete=?";
        Paquete paqueteEncontrado = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPaquete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ciudad origen = new Ciudad();
                Ciudad destino = new Ciudad();
                CiudadData city = new CiudadData();

               // JOptionPane.showMessageDialog(null, "Se ha encontrado un Paquete con ese ID");
                paqueteEncontrado = new Paquete();
                Alojamiento alojamiento = new Alojamiento();
                Pasaje pasaje = new Pasaje();
                PasajeData pass=new PasajeData();
                AlojamientoData aloj = new AlojamientoData();

                paqueteEncontrado.setIdPaquete(rs.getInt("idPaquete"));
                origen = city.buscarCiudad(rs.getString("origen"));
                destino = city.buscarCiudad(rs.getString("destino"));
                paqueteEncontrado.setOrigen(origen);
                paqueteEncontrado.setDestino(destino);

                alojamiento = aloj.buscarAlojamiento(rs.getInt("idAlojamiento"));
                paqueteEncontrado.setAlojamiento(alojamiento);
                pasaje = pass.buscarPasaje(rs.getInt("idPasaje"));
                paqueteEncontrado.setPasaje(pasaje);
                
                paqueteEncontrado.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());
                System.out.println(paqueteEncontrado.getFechaSalida());
                paqueteEncontrado.setFechaLLegada(rs.getDate("fechaLlegada").toLocalDate());
                System.out.println(paqueteEncontrado.getFechaLLegada());
                
                paqueteEncontrado.setEstado(rs.getBoolean("estado"));
                paqueteEncontrado.setCantPersonas(rs.getInt("cantPersonas"));
                paqueteEncontrado.setImporte(rs.getDouble("Importe"));
                paqueteEncontrado.setCliente(rs.getString("Cliente"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al Acceder a la base de datos de Paquetes" + ex.getMessage());
        }
        return paqueteEncontrado;
    }
    
    public void modificarPaquete(Paquete paquete) {
        String sql = "UPDATE paquete SET origen = ?, Destino = ?, idAlojamiento = ?,idPasaje = ?,"
                + "fechaSalida = ?, fechaLlegada = ?, estado = ?, cantPersonas = ?, Importe = ?,"
                + "Cliente = ? WHERE idPaquete = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paquete.getOrigen().getNombre());
            ps.setString(2, paquete.getDestino().getNombre());
            ps.setInt(3, paquete.getAlojamiento().getIdAlojamiento());
            ps.setInt(4, paquete.getPasaje().getIdPasaje());
            ps.setDate(5, Date.valueOf(paquete.getFechaSalida()));
            ps.setDate(6, Date.valueOf(paquete.getFechaLLegada()));
            ps.setBoolean(7, paquete.isEstado());
            ps.setInt(8, paquete.getCantPersonas());
            ps.setDouble(9,paquete.getImporte());
            ps.setString(10, paquete.getCliente());
            ps.setInt(11, paquete.getIdPaquete());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Se ha Modificado el Paquete " + paquete.getIdPaquete());
            
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Paquete" + ex.getMessage());
        }
    }

}
