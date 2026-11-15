/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taquiya.modelo;

/**
 *
 * @author nerea
 */
//CREAMOS ATRIBUTOS DE USUARIO, IGUAL QUE COM LO TENEMOS EN EL SCRIPT
public class Usuario {
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;
    private String correo;
    private int telefono;

    //CREAMOS CONSTRUCTOR CON TODOS LOS ATRIBUTOS
    public Usuario(String dni, String nombre, String apellido1, String apellido2, String direccion, String correo, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    //CREAMOS LOS GETTERS
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public int getTelefono() {
        return telefono;
    }
    
}
