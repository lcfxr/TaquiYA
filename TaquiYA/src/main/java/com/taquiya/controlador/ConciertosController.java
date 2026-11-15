package com.taquiya.controlador;

import com.taquiya.App;
import com.taquiya.dao.ConciertoDAO;
import com.taquiya.modelo.Concierto;
import com.taquiya.modelo.Sesion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConciertosController implements Initializable {

    @FXML
    private TextField txtCiudad; //LOS ATRIBUTOS IGUAL QUE HEMOS PUESTO EN EL SCENE BUILDER
    @FXML
    private TableView<Concierto> tablaConciertos;
    @FXML
    private TableColumn<Concierto, String> colFecha;
    @FXML
    private TableColumn<Concierto, String> colHora;
    @FXML
    private TableColumn<Concierto, String> colLugar;
    @FXML
    private TableColumn<Concierto, String> colCiudad;
    @FXML
    private TableColumn<Concierto, String> colGrupos;

    private ConciertoDAO dao = new ConciertoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));//PARA AÑADIR EN CADA COLUMNA EL DATO NECESARIO
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colGrupos.setCellValueFactory(new PropertyValueFactory<>("grupos"));

        tablaConciertos.setItems(FXCollections.observableArrayList(dao.listarProximos()));
    }

    @FXML
    private void buscar(ActionEvent event) {//BUSQUEDA DE LOS CONCIERTOS, SI ESTÁ VACÍO MUESTRA TODOS Y SI NO, LOS DE ESA CIUDAD
        String ciudad = txtCiudad.getText();
        if (ciudad.isEmpty()) {
            tablaConciertos.setItems(FXCollections.observableArrayList(dao.listarProximos()));
        } else {
            tablaConciertos.setItems(FXCollections.observableArrayList(dao.buscarPorCiudad(ciudad)));
        }
    }

    @FXML
    private void irACompra(ActionEvent event) {//TE DIRIGE A LA PAGINA DE COMPRA, SI NO HAS ELEGIDO CONCIERTO SALE UN AVISO
        Concierto elegido = tablaConciertos.getSelectionModel().getSelectedItem();

        if (elegido == null) {
            Alert aviso = new Alert(Alert.AlertType.WARNING);
            aviso.setHeaderText(null);
            aviso.setContentText("Selecciona un concierto de la tabla");
            aviso.showAndWait();
        } else {
            Sesion.concierto = elegido;
            try {
                App.setRoot("compra");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
