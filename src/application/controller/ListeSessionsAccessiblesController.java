package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.utility.CandidatureGeneriqueTblModel;
import application.utility.DialogUtil;
import application.utility.RESTClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListeSessionsAccessiblesController implements Initializable {

    private ObservableList<CandidatureGeneriqueTblModel>      listeSessionGenerique = FXCollections
            .observableArrayList();
    @FXML
    private TableView<CandidatureGeneriqueTblModel>           sessionGeneriqueTable;
    @FXML
    private TableColumn<CandidatureGeneriqueTblModel, Number> id_Session;
    @FXML
    private TableColumn<CandidatureGeneriqueTblModel, String> nom;
    @FXML
    private TableColumn<CandidatureGeneriqueTblModel, String> dateDebut;
    @FXML
    private TableColumn<CandidatureGeneriqueTblModel, String> dateFin;
    @FXML
    private TableColumn<CandidatureGeneriqueTblModel, String> uv;
    @FXML
    private TableColumn<CandidatureGeneriqueTblModel, String> stage;
    @FXML
    private Label                                             agentMatricule;
    @FXML
    private Label                                             agentNom;
    @FXML
    private Label                                             agentUUID;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        initializeAgent();
        initializeTable();
        retrieveAccessibleSessions();
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

    public void initializeTable() {
        id_Session.setCellValueFactory( cellData -> cellData.getValue().getId_SessionProperty() );
        nom.setCellValueFactory( cellData -> cellData.getValue().getNomProperty() );
        dateDebut.setCellValueFactory( cellData -> cellData.getValue().getDateDebutProperty() );
        dateFin.setCellValueFactory( cellData -> cellData.getValue().getDateFinProperty() );
        uv.setCellValueFactory( cellData -> cellData.getValue().getUvProperty() );
        stage.setCellValueFactory( cellData -> cellData.getValue().getStageProperty() );
    }

    public void retrieveAccessibleSessions() {
        try {
            listeSessionGenerique = FXCollections.observableArrayList( RESTClient.findAccessibleSessions() );
            sessionGeneriqueTable.setItems( listeSessionGenerique );
        } catch ( RuntimeException e ) {
            DialogUtil.buildExceptionDialog( "Erreur", "Erreur de connexion", e )
                    .showAndWait();
            System.exit( -1 );
        }
    }
}
