
package com.taquiya.modelo;

public class Concierto {

    //CREAMOS LOS ATRIBUTOS DE CONCIERTO IGUAL QUE TENIAMOS EN EL SCRITP
    private String idConcierto;
    private String fecha;
    private String hora;
    private String lugar;
    private String ciudad;
    private String grupos;

    //CONSTRUCTOR CON TODOS LOS ATRIBUTOS

    public Concierto(String idConcierto, String fecha, String hora, String lugar, String ciudad, String grupos) {
        this.idConcierto = idConcierto;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.ciudad = ciudad;
        this.grupos = grupos;
    }
    

    //GETTERS
    public String getIdConcierto() {
        return idConcierto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getLugar() {
        return lugar;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getGrupos() {
        return grupos;
    }
    
    
    

}
