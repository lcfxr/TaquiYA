
package com.taquiya.dao;

import com.taquiya.modelo.Concierto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConciertoDAO {

    // Próximos conciertos (consulta 2)
    public List<Concierto> listarProximos() {
        List<Concierto> lista = new ArrayList<>();
        String sql = "select c.id_concierto, c.fecha, c.hora, l.nombre, l.ciudad " //ESTA ES LA CONSULTA QUE HICIMOS EN EL SCRIPT
                   + "from concierto c join lugar l on c.id_lugar = l.id_lugar "
                   + "where c.fecha >= curdate() order by c.fecha";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Concierto(
                    rs.getString("id_concierto"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("nombre"),
                    rs.getString("ciudad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
    
        // BUSCAR CONCIERTOS POR CIUDAD
    public List<Concierto> buscarPorCiudad(String ciudad) {
        List<Concierto> lista = new ArrayList<>();
        String sql = "select c.id_concierto, c.fecha, c.hora, l.nombre, l.ciudad "
                   + "from concierto c join lugar l on c.id_lugar = l.id_lugar "
                   + "where c.fecha >= curdate() and l.ciudad = ? "    
                   + "order by c.fecha";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1,ciudad);                                
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Concierto(
                    rs.getString("id_concierto"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("nombre"),
                    rs.getString("ciudad")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}