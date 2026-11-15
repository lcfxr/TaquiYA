
package com.taquiya.controlador;

import com.taquiya.App;
import com.taquiya.dao.EntradaDAO;
import com.taquiya.modelo.Concierto;
import com.taquiya.modelo.Sesion;
import com.taquiya.modelo.TipoEntrada;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CompraController implements Initializable {

    //CREACION DE LOS ATRIBUTOS DE SCENEBUILDER
    @FXML private Label lblConcierto;
    @FXML private ComboBox<TipoEntrada> cmbTipo;
    @FXML private Label lblDisponibles;
    @FXML private Label lblMensaje;

    private EntradaDAO dao = new EntradaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {//LO QUE HACE ES QUE COGE EL CONCIERTO GUARDADO EN SESION, MUESTRA LOS DATOS Y LOS TIPOS DE ENTRADA
        Concierto c = Sesion.concierto;  
        lblConcierto.setText("Concierto: " + c.getFecha() + " - " + c.getLugar() + " (" + c.getCiudad() + ")");
        cmbTipo.setItems(FXCollections.observableArrayList(dao.listarTipos(c.getIdConcierto())));
    }

    
    @FXML
    private void mostrarDisponibles(ActionEvent event) {//PARA MOSTRAR LAS ENTRADAS DISPONIBLES
        TipoEntrada tipo = cmbTipo.getValue();
        if (tipo != null) {
            lblDisponibles.setText("Quedan " + dao.disponibles(tipo.getIdTipo()) + " entradas");
        }
    }

    // TRANSACCION DE COMPRAR
    @FXML
    private void comprar(ActionEvent event) {//SI ELEGIMOS LA ENTRADA Y ESTA DISPONIBLE NOS APARECE COMO COMPRADA
        TipoEntrada tipo = cmbTipo.getValue();
        if (tipo == null) {
            lblMensaje.setText("Elige pista o grada");
        } else {
            String resultado = dao.comprar(Sesion.usuario.getDni(), tipo.getIdTipo());
            lblMensaje.setText(resultado);
            mostrarDisponibles(null);   
        }
    }

    @FXML
    private void volver(ActionEvent event) {//VUELVE A LA PANTALLA DE CONCIERTOS
        try {
            App.setRoot("conciertos");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
