package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.SapforClient;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

    @FXML
    private BorderPane                  mainBorderPane;
    @FXML
    private AnchorPane                  MenuBar;
    @FXML
    private Menu                        menuGestionnaire;
    @FXML
    private Menu                        menuCandidature;
    @FXML
    private MenuItem                    sessionsAccessibles;
    @FXML
    private LoginViewController         loginViewController;

    public static SimpleBooleanProperty gestionnaire = new SimpleBooleanProperty( false );
    public static SimpleBooleanProperty auth         = new SimpleBooleanProperty( false );

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        menuGestionnaire.disableProperty().bind( gestionnaire.not() );
        menuCandidature.disableProperty().bind( auth.not() );
        sessionsAccessibles.disableProperty().bind( auth.not() );
    }

    @FXML
    private void handlerActionClicConnexion( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    SapforClient.class.getResource( "view/LoginView.fxml" ) );
            AnchorPane loginPane = (AnchorPane) loader.load();
            mainBorderPane.setCenter( loginPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicDeposerCandidature( final ActionEvent event ) {
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
    private void handlerActionClicRetirerCandidature( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/TableCandidatures.fxml" ) );
            AnchorPane sessionsPane = (AnchorPane) loader.load();

            mainBorderPane.setCenter( sessionsPane );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicListeFermee( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/TableSessionsFermees.fxml" ) );
            AnchorPane sessionsPane = (AnchorPane) loader.load();

            mainBorderPane.setCenter( sessionsPane );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicListeOuverte( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/TableSessionsOuvertes.fxml" ) );
            AnchorPane sessionsPane = (AnchorPane) loader.load();

            mainBorderPane.setCenter( sessionsPane );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicSelectionCandidats( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/D3SelectionCandidats.fxml" ) );
            BorderPane selectionPane = (BorderPane) loader.load();
            mainBorderPane.setCenter( selectionPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handlerActionClicFermerCandidature( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/D1TableFermetureCandidature.fxml" ) );
            AnchorPane selectionPane = (AnchorPane) loader.load();
            mainBorderPane.setCenter( selectionPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handlerActionClicOuvrirCandidature( final ActionEvent event ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/D2TableOuvrirCandidature.fxml" ) );
            AnchorPane selectionPane = (AnchorPane) loader.load();
            mainBorderPane.setCenter( selectionPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}