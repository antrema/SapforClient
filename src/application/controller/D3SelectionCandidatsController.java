package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.SapforClient;
import application.beans.CandidatGeneriqueTblModel;
import application.utility.DialogUtil;
import application.utility.RESTClient;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class D3SelectionCandidatsController implements Initializable {

    @FXML
    private BorderPane                           selectionCandidatsBorderPane;
    @FXML
    private BorderPane                           D3TableSelectionCandidats;
    @FXML
    private Label                                agentMatricule;
    @FXML
    private Label                                agentNom;
    @FXML
    private Label                                agentUUID;
    @FXML
    private Button                               BtnCandidater;
    @FXML
    private D3aTableSelectionCandidatsController d3aTableSelectionCandidatsController;
    @FXML
    private D3bTableSelectionCandidatsController d3bTableSelectionCandidatsController;
    @FXML
    private D3cTableSelectionCandidatsController d3cTableSelectionCandidatsController;
    @FXML
    private Button                               BtnSuivant;
    @FXML
    private Button                               BtnPrecedent;
    ObservableList<CandidatGeneriqueTblModel>    listeCandidature;
    ObservableList<CandidatGeneriqueTblModel>    listeCandidatureFinale;
    private int                                  pageActuelle;
    private int                                  session;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        pageActuelle = 0;
        initializeView();
        initializeAgent();
        initializeTable();
    }

    public void initializeAgent() {
        if ( RESTClient.getMatricule().compareTo( "" ) == 0 ) {
            agentMatricule.setText( "Nom connecté" );
        } else {
            agentMatricule.setText( RESTClient.getMatricule() );
        }

        if ( RESTClient.getNom().compareTo( "" ) == 0 ) {
            agentNom.setText( "Non connecté" );

        } else {
            agentNom.setText( RESTClient.getNom() );

        }
        if ( RESTClient.getUuid().compareTo( "" ) == 0 ) {
            agentUUID.setText( "Non connecté" );

        } else {
            agentUUID.setText( RESTClient.getUuid() );
        }
    }

    public void initializeView() {

    }

    public void initializeTable() {
        switch ( pageActuelle ) {
        case 0:
            BtnPrecedent.setVisible( false );
            printSessionTable();
            break;
        case 1:
            BtnPrecedent.setVisible( true );
            printCandidatsTable();
            break;
        case 2:
            BtnPrecedent.setVisible( true );
            printAttenteTable();
            break;
        case 3:
            RESTClient.setListeCandidats( validationListeCandidats(), session );
            DialogUtil.buildSimpleDialog( "Candidatures mises a jour dans la BDD" ).showAndWait();
            printDefaultPage();
            break;
        default:
            BtnPrecedent.setVisible( false );
            printSessionTable();
            break;
        }
    }

    public void printSessionTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/D3aTableSelectionCandidats.fxml" ) );
            AnchorPane sessionSelectionPane = (AnchorPane) loader.load();
            d3aTableSelectionCandidatsController = loader.getController();
            D3TableSelectionCandidats.setCenter( sessionSelectionPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public void printCandidatsTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/D3bTableSelectionCandidats.fxml" ) );
            session = d3aTableSelectionCandidatsController.getTableView().getSelectionModel().getSelectedItem()
                    .getId();
            AnchorPane sessionSelectionPane = (AnchorPane) loader.load();
            d3bTableSelectionCandidatsController = loader.getController();
            d3bTableSelectionCandidatsController.setIdSession( session );
            d3bTableSelectionCandidatsController.retrieveAllCandidatsSessions();
            D3TableSelectionCandidats.setCenter( sessionSelectionPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public void printAttenteTable() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/D3cTableSelectionCandidats.fxml" ) );
            listeCandidature = d3bTableSelectionCandidatsController.getTableView().getItems();
            AnchorPane sessionSelectionPane = (AnchorPane) loader.load();
            d3cTableSelectionCandidatsController = loader.getController();
            d3cTableSelectionCandidatsController.setListeCandidatGenerique( listeCandidature );
            d3cTableSelectionCandidatsController.retrieveAllCandidatsSessions();
            D3TableSelectionCandidats.setCenter( sessionSelectionPane );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public List<CandidatGeneriqueTblModel> validationListeCandidats() {
        listeCandidatureFinale = d3cTableSelectionCandidatsController.getListeStatuee();
        List<CandidatGeneriqueTblModel> liste = d3cTableSelectionCandidatsController.getListeClassee();

        for ( CandidatGeneriqueTblModel c : liste ) {
            listeCandidatureFinale.add( c );
        }
        return listeCandidatureFinale;
    }

    public void printDefaultPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/TableVide.fxml" ) );
            AnchorPane sessionSelectionPane = (AnchorPane) loader.load();
            D3TableSelectionCandidats.setCenter( sessionSelectionPane );
            BtnSuivant.setVisible( false );
            BtnPrecedent.setVisible( false );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlerActionClicSuivant( final ActionEvent event ) {
        pageActuelle++;
        initializeTable();
    }

    @FXML
    private void handlerActionClicPrecedent( final ActionEvent event ) {
        pageActuelle--;
        initializeTable();
    }

}
