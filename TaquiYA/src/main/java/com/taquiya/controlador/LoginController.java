package com.taquiya.controlador;

import com.taquiya.dao.UsuarioDAO;
import com.taquiya.modelo.Usuario;
import javafx.scene.control.Label;  
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private TextField txtCorreo;     //AÑADIMOS LO QUE HEMOS CREADO EN EL SCENEBUILDER
    @FXML
    private Label lblMensaje;         

    @FXML
    private void entrar(ActionEvent event) {//METODO DEL BOTO9N ENTRAR
        String correo = txtCorreo.getText();         

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarPorCorreo(correo);            

        if (usuario == null) {                       
            lblMensaje.setText("El usuario no existe");
        } else {                                      
            lblMensaje.setText("Bienvenida, " + usuario.getNombre());
        }
    }

}
