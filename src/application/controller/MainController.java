package application.controller;

import java.io.IOException;

import application.SapforClient;
import application.beans.AgentConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {

    private AgentConnection ag = new AgentConnection();

    @FXML
    private BorderPane      mainBorderPane;

    @FXML
    private void handlerActionClicConnexion( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/LoginView.fxml" ) );
            AnchorPane loginPane = (AnchorPane) loader.load();

            mainBorderPane.setCenter( loginPane );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicListeSessions( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/TableSessions.fxml" ) );
            AnchorPane sessionsPane = (AnchorPane) loader.load();

            mainBorderPane.setCenter( sessionsPane );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicListeSessionsAccessibles( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/TableSessionsAccessibles.fxml" ) );
            AnchorPane sessionsPane = (AnchorPane) loader.load();

            mainBorderPane.setCenter( sessionsPane );

        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handlerActionClicListeCandidatures( final ActionEvent event ) {
    }

    public AgentConnection getAg() {
        return ag;
    }

    public void setAg( AgentConnection ag ) {
        this.ag = ag;
    }

}