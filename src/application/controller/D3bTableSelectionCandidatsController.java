package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.beans.CandidatGeneriqueTblModel;
import application.utility.DialogUtil;
import application.utility.RESTClient;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class D3bTableSelectionCandidatsController implements Initializable {

    private int                                             idSession;
    private int                                             compteurListeAttente;
    private ObservableList<CandidatGeneriqueTblModel>       listeCandidatGenerique = FXCollections
            .observableArrayList();
    @FXML
    private TableView<CandidatGeneriqueTblModel>            candidatGeneriqueTable;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Number>  id_Agent;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, String>  nom;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, String>  matricule;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, String>  role;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Number>  statut;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Boolean> accepte;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Boolean> refuse;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Boolean> attente;

    @FXML
    private Node                                            d3bTableSelectionCandidats;

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession( int s ) {
        idSession = s;
    }

    public TableView<CandidatGeneriqueTblModel> getTableView() {
        return candidatGeneriqueTable;
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        initializeTable();
        compteurListeAttente = 0;
    }

    public void initializeTable() {
        candidatGeneriqueTable.setEditable( true );
        id_Agent.setCellValueFactory( cellData -> cellData.getValue().getId_AgentProperty() );
        nom.setCellValueFactory( cellData -> cellData.getValue().getNomProperty() );
        matricule.setCellValueFactory( cellData -> cellData.getValue().getMatriculeProperty() );
        role.setCellValueFactory( cellData -> cellData.getValue().getRoleProperty() );
        statut.setCellValueFactory( cellData -> cellData.getValue().getStatutCandidatureProperty() );

        accepte.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new SimpleBooleanProperty( p.getValue() != null );
                    }
                } );

        refuse.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new SimpleBooleanProperty( p.getValue() != null );
                    }
                } );

        attente.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new SimpleBooleanProperty( p.getValue() != null );
                    }
                } );

        // Definition des CellFactory
        accepte.setCellFactory(
                new Callback<TableColumn<CandidatGeneriqueTblModel, Boolean>, TableCell<CandidatGeneriqueTblModel, Boolean>>() {
                    @Override
                    public TableCell<CandidatGeneriqueTblModel, Boolean> call(
                            TableColumn<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new TableCell<CandidatGeneriqueTblModel, Boolean>() {
                            private Button button = new Button( "Accepter" );
                            {
                                button.setMaxWidth( 75.0 );
                                button.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle( ActionEvent t ) {
                                        final CandidatGeneriqueTblModel item = (CandidatGeneriqueTblModel) getTableRow()
                                                .getItem();
                                        item.setAccepte( true );
                                        item.setRefus( false );
                                        item.setListeAttente( false );
                                        button.underlineProperty().bind( ( item.getRefusProperty().not() )
                                                .and( item.getListeAttenteProperty().not() ) );
                                        item.setStatutCandidature( -1 );
                                    }
                                } );
                            }

                            @Override
                            protected void updateItem( Boolean value, boolean empty ) {
                                super.updateItem( value, empty );
                                final String text = null;
                                setText( null );
                                final Node graphic = ( empty ) ? null : button;
                                setGraphic( graphic );
                            }
                        };
                    }
                } );

        refuse.setCellFactory(
                new Callback<TableColumn<CandidatGeneriqueTblModel, Boolean>, TableCell<CandidatGeneriqueTblModel, Boolean>>() {
                    @Override
                    public TableCell<CandidatGeneriqueTblModel, Boolean> call(
                            TableColumn<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new TableCell<CandidatGeneriqueTblModel, Boolean>() {
                            private Button button = new Button( "Refuser" );
                            {
                                button.setMaxWidth( 75.0 );
                                button.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle( ActionEvent t ) {
                                        final CandidatGeneriqueTblModel item = (CandidatGeneriqueTblModel) getTableRow()
                                                .getItem();
                                        item.setAccepte( false );
                                        item.setRefus( true );
                                        item.setListeAttente( false );
                                        button.underlineProperty().bind( ( item.getAccepteProperty().not() )
                                                .and( item.getListeAttenteProperty().not() ) );
                                        item.setStatutCandidature( 0 );
                                    }
                                } );
                            }

                            @Override
                            protected void updateItem( Boolean value, boolean empty ) {
                                super.updateItem( value, empty );
                                final String text = null;
                                setText( null );
                                final Node graphic = ( empty ) ? null : button;
                                setGraphic( graphic );
                            }
                        };
                    }
                } );

        attente.setCellFactory(
                new Callback<TableColumn<CandidatGeneriqueTblModel, Boolean>, TableCell<CandidatGeneriqueTblModel, Boolean>>() {
                    @Override
                    public TableCell<CandidatGeneriqueTblModel, Boolean> call(
                            TableColumn<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new TableCell<CandidatGeneriqueTblModel, Boolean>() {
                            private Button button = new Button( "Attente" );
                            {
                                button.setMaxWidth( 75.0 );
                                button.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle( ActionEvent t ) {
                                        final CandidatGeneriqueTblModel item = (CandidatGeneriqueTblModel) getTableRow()
                                                .getItem();
                                        item.setAccepte( false );
                                        item.setRefus( false );
                                        item.setListeAttente( true );
                                        button.underlineProperty().bind( ( item.getAccepteProperty().not() )
                                                .and( item.getRefusProperty().not() ) );
                                        compteurListeAttente++;
                                        item.setStatutCandidature( compteurListeAttente );
                                    }
                                } );
                            }

                            @Override
                            protected void updateItem( Boolean value, boolean empty ) {
                                super.updateItem( value, empty );
                                final String text = null;
                                setText( null );
                                final Node graphic = ( empty ) ? null : button;
                                setGraphic( graphic );
                            }
                        };
                    }
                } );
    }

    public void retrieveAllCandidatsSessions() {
        try {
            listeCandidatGenerique = FXCollections.observableArrayList( RESTClient.findCandidat( idSession ) );
            candidatGeneriqueTable.setItems( listeCandidatGenerique );
        } catch ( RuntimeException e ) {
            DialogUtil.buildExceptionDialog( "Erreur", "Erreur de connexion", e )
                    .showAndWait();
            System.exit( -1 );
        }
    }
}
