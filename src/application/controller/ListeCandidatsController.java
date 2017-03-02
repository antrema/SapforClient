package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.utility.CandidatGeneriqueTblModel;
import application.utility.DialogUtil;
import application.utility.RESTClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListeCandidatsController implements Initializable {

    private int                                            nbListeAttente         = 0;
    private ObservableList<CandidatGeneriqueTblModel>      listeCandidatGenerique = FXCollections
            .observableArrayList();
    @FXML
    private TableView<CandidatGeneriqueTblModel>           candidatGeneriqueTable;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Number> id_Agent;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, String> nom;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, String> matricule;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, String> role;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Number> statut;
    @FXML
    private Label                                          session;
    @FXML
    private Label                                          agentMatricule;
    @FXML
    private Label                                          agentNom;
    @FXML
    private Label                                          agentUUID;
    @FXML
    private Button                                         BtnAccepte;
    @FXML
    private Button                                         BtnRefuse;
    @FXML
    private Button                                         BtnAttente;
    @FXML
    private Button                                         BtnNext;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        initializeAgent();
        initializeTable();
        retrieveAllCandidats( Integer.parseInt( session.getText() ) );
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
        id_Agent.setCellValueFactory( cellData -> cellData.getValue().getId_AgentProperty() );
        nom.setCellValueFactory( cellData -> cellData.getValue().getNomProperty() );
        matricule.setCellValueFactory( cellData -> cellData.getValue().getMatriculeProperty() );
        role.setCellValueFactory( cellData -> cellData.getValue().getRoleProperty() );
        statut.setCellValueFactory( cellData -> cellData.getValue().getStatutCandidatureProperty() );
    }

    public void retrieveAllCandidats( int idSession ) {
        try {
            listeCandidatGenerique = FXCollections
                    .observableArrayList( RESTClient.findCandidat( idSession ) );
            candidatGeneriqueTable.setItems( listeCandidatGenerique );
        } catch ( RuntimeException e ) {
            DialogUtil.buildExceptionDialog( "Erreur", "Erreur de connexion", e )
                    .showAndWait();
            System.exit( -1 );
        }
    }

    @FXML
    private void handlerClicAccepte( final ActionEvent event ) {
        candidatGeneriqueTable.getSelectionModel().getSelectedItem().setStatutCandidature( -1 );
    }

    @FXML
    private void handlerClicRefuse( final ActionEvent event ) {
        candidatGeneriqueTable.getSelectionModel().getSelectedItem().setStatutCandidature( 0 );
    }

    @FXML
    private void handlerClicAttente( final ActionEvent event ) {
        candidatGeneriqueTable.getSelectionModel().getSelectedItem().setStatutCandidature( nbListeAttente );
        nbListeAttente++;
    }

    @FXML
    private void handlerClicNext( final ActionEvent event ) {
        // TODO
        /*
         * String session =
         * sessionGeneriqueTable.getSelectionModel().getSelectedItem().
         * getId_Session().toString(); int selectedIndex =
         * sessionGeneriqueTable.getSelectionModel().getSelectedIndex();
         * RESTClient.RetraitCandidature( session );
         * sessionGeneriqueTable.getItems().remove( selectedIndex );
         */
    }

}
