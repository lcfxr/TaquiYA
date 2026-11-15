package com.taquiya.dao;
//CON ESTA CLASE UNIMOS A LA BSE DE DATOS PARA QUE HAYA CONEXION Y ENCUENTRE A LOS USUARIOS

import com.taquiya.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO {

    public Usuario buscarPorCorreo(String correo) {
        String sql = "select * from usuario where correo = ?";   // AQUI PONEMOS LA PRIMERA CONSULTA QUE HICIMOS PARA QUE NOS DIERA AL USUARIO POR SU CORREO

        try (Connection con = ConexionBD.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {//ESTE ES EL COMANDO PARA LA CONCEXION DE LA CONSULTA

            ps.setString(1, correo);          
            ResultSet rs = ps.executeQuery();  

            if (rs.next()) {   //SI ESTA, TE LO MUESTRA               
                return new Usuario(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("direccion"),
                        rs.getString("correo"),
                        rs.getInt("telefono")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;   
    }
}
