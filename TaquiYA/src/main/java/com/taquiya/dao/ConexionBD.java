package com.taquiya.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//ESTA CLASE ES PARA CREAR LA CONCEXION MYSQL CON LA BASE DE DATOS QUE YA HABIAMOS CREADO
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/taquiya";
    private static final String USUARIO = "taquiya";
    private static final String CONTRASENA = "taquiya123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
