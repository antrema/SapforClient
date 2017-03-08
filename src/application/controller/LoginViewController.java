package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.beans.AgentConnection;
import application.utility.RESTClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        @SuppressWarnings( "unused" )
        AgentConnection agentConnection = RESTClient.getIdentification( checkUser, checkPw );
        if ( agentConnection != null ) {
            okMessage();
            MainController.auth.set( true );
            MainController.gestionnaire.set( agentConnection.getGestionnaire() );
        } else {
            nokMessage();
        }
    }

    public void okMessage() {
        Alert alert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Information" );
        alert.setHeaderText( null );
        alert.setContentText( "Connexion reussie" );
        alert.showAndWait();
    }

    public void nokMessage() {
        Alert alert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Information" );
        alert.setHeaderText( null );
        alert.setContentText( "Echec de connexion" );
        alert.showAndWait();
    }
}
