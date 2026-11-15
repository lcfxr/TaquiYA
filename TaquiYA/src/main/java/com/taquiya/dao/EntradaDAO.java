
package com.taquiya.dao;

import com.taquiya.modelo.TipoEntrada;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EntradaDAO {

    //CONSULTA QUE DEVUELVE LOS TIPOS DE ENTRADA (PISTA O GRADA) DE UN CONCIERTO CON SU PRECIO
    public List<TipoEntrada> listarTipos(String idConcierto) {
        List<TipoEntrada> lista = new ArrayList<>();
        String sql = "select id_tipo, nombre, precio from tipo_entrada where id_concierto = ?"; //AQUI EN EL ? PONEMOS EL ID DEL CONCIERTRO

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idConcierto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new TipoEntrada(
                    rs.getString("id_tipo"),
                    rs.getString("nombre"),
                    rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    //LLAMA AL PROCEDIMIENTO ENTRADAS_DISPONIBLES PARA SABER CUANTAS ENTRADAS QUEDAN DE UN TIPO
    public int disponibles(String idTipo) {
        try (Connection con = ConexionBD.conectar();
             CallableStatement cs = con.prepareCall("{call entradas_disponibles(?, ?)}")) {

            cs.setString(1, idTipo);                  
            cs.registerOutParameter(2, Types.INTEGER);    
            cs.execute();
            return cs.getInt(2);                          
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }


    //LLAMA A LA TRANSACCION COMPRAR_ENTRADA PARA COMPRAR UNA ENTRADA
    //SI NO QUEDAN ENTRADAS SE HACE ROLLBACK Y DEVUELVE EL MENSAJE DE ERROR
    public String comprar(String dni, String idTipo) {
        try (Connection con = ConexionBD.conectar();
             CallableStatement cs = con.prepareCall("{call comprar_entrada(?, ?)}")) {

            cs.setString(1, dni);
            cs.setString(2, idTipo);
            cs.execute();
            return "Compra realizada";
        } catch (SQLException e) {
            return e.getMessage();   
        }
    }
}
