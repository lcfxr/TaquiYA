package com.taquiya.controlador;

import com.taquiya.dao.ConciertoDAO;
import com.taquiya.modelo.Concierto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConciertosController implements Initializable {

    @FXML
    private TextField txtCiudad; //LOS ATRIBUTOS IGUAL QUE HEMMOS PUESTO EN EL SCENE BUILDER
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

    private ConciertoDAO dao = new ConciertoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        tablaConciertos.setItems(FXCollections.observableArrayList(dao.listarProximos()));
    }

    @FXML
    private void buscar(ActionEvent event) {
        String ciudad = txtCiudad.getText();
        if (ciudad.isEmpty()) {
            tablaConciertos.setItems(FXCollections.observableArrayList(dao.listarProximos()));
        } else {
            tablaConciertos.setItems(FXCollections.observableArrayList(dao.buscarPorCiudad(ciudad)));
        }
    }

    @FXML
    private void irACompra(ActionEvent event) {
        // lo haremos después
    }
}
