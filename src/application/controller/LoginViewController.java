package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.beans.AgentConnection;
import application.utility.DialogUtil;
import application.utility.RESTClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

    @FXML
    private PasswordField TxtPwd;

    @FXML
    private TextField     TxtLogin;

    @FXML
    private Button        BtnValidation;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
    }

    @FXML
    private void handlerClicConnexion( final ActionEvent event ) {
        String checkUser = TxtLogin.getText().toString();
        String checkPw = TxtPwd.getText().toString();
        AgentConnection agentConnection = RESTClient.getIdentification( checkUser, checkPw );
        if ( agentConnection != null ) {
            DialogUtil.buildSimpleDialog( "Connexion Reussie" ).showAndWait();
            MainController.auth.set( true );
            MainController.gestionnaire.set( agentConnection.getGestionnaire() );
        } else {
            DialogUtil.buildSimpleDialog( "Echec de Connexion" ).showAndWait();
        }
    }
}
