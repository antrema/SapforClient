package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.beans.SessionGeneriqueTblModel;
import application.utility.DialogUtil;
import application.utility.RESTClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class D3aTableSelectionCandidatsController implements Initializable {

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
    @FXML
    private Node                                          d3aTableSelectionCandidats;

    public TableView<SessionGeneriqueTblModel> getTableView() {
        return sessionGeneriqueTable;
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        initializeTable();
        retrieveAllClosedSessions();
    }

    public void initializeTable() {
        id.setCellValueFactory( cellData -> cellData.getValue().getIdProperty() );
        nom.setCellValueFactory( cellData -> cellData.getValue().getNomProperty() );
        dateDebut.setCellValueFactory( cellData -> cellData.getValue().getDateDebutProperty() );
        dateFin.setCellValueFactory( cellData -> cellData.getValue().getDateFinProperty() );
        uv.setCellValueFactory( cellData -> cellData.getValue().getUvProperty() );
        stage.setCellValueFactory( cellData -> cellData.getValue().getStageProperty() );
    }

    public void retrieveAllClosedSessions() {
        try {
            listeSessionGenerique = FXCollections.observableArrayList( RESTClient.findAllClosedSessions() );
            sessionGeneriqueTable.setItems( listeSessionGenerique );
        } catch ( RuntimeException e ) {
            DialogUtil.buildExceptionDialog( "Erreur de connexion", e )
                    .showAndWait();
            System.exit( -1 );
        }
    }
}
