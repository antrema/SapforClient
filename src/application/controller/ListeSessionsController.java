package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.utility.DialogUtil;
import application.utility.RESTClient;
import application.utility.SessionGeneriqueTblModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListeSessionsController implements Initializable {

    private ObservableList<SessionGeneriqueTblModel>      listeSessionGenerique = FXCollections.observableArrayList();
    @FXML
    private TableView<SessionGeneriqueTblModel>           sessionGeneriqueTable;
    @FXML
    private TableColumn<SessionGeneriqueTblModel, Number> id;
    @FXML
    private TableColumn<SessionGeneriqueTblModel, String> nom;
    @FXML
    private TableColumn<SessionGeneriqueTblModel, String> dateDebut;
    @FXML
    private TableColumn<SessionGeneriqueTblModel, String> dateFin;
    @FXML
    private TableColumn<SessionGeneriqueTblModel, String> uv;
    @FXML
    private TableColumn<SessionGeneriqueTblModel, String> stage;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        initializeTable();
        retrieveAllSessions();
    }

    public void initializeTable() {
        id.setCellValueFactory( cellData -> cellData.getValue().getIdProperty() );
        nom.setCellValueFactory( cellData -> cellData.getValue().getNomProperty() );
        dateDebut.setCellValueFactory( cellData -> cellData.getValue().getDateDebutProperty() );
        dateFin.setCellValueFactory( cellData -> cellData.getValue().getDateFinProperty() );
        uv.setCellValueFactory( cellData -> cellData.getValue().getUvProperty() );
        stage.setCellValueFactory( cellData -> cellData.getValue().getStageProperty() );
    }

    public void retrieveAllSessions() {
        try {
            listeSessionGenerique = FXCollections.observableArrayList( RESTClient.findAllSessions() );
            sessionGeneriqueTable.setItems( listeSessionGenerique );
        } catch ( RuntimeException e ) {
            DialogUtil.buildExceptionDialog( "Erreur", "Erreur de connexion", e )
                    .showAndWait();
            System.exit( -1 );
        }
    }
}
