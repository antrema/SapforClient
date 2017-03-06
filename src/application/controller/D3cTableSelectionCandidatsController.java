package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.beans.CandidatGeneriqueTblModel;
import application.utility.DialogUtil;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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

public class D3cTableSelectionCandidatsController implements Initializable {

    private ObservableList<CandidatGeneriqueTblModel>       listeCandidatGenerique        = FXCollections
            .observableArrayList();
    private ObservableList<CandidatGeneriqueTblModel>       listeCandidatGeneriqueAttente = FXCollections
            .observableArrayList();
    // private ObservableList<CandidatGeneriqueTblModel>
    // listeCandidatGeneriqueAttente ;
    private ObservableList<CandidatGeneriqueTblModel>       listeCandidatGeneriqueAttenteTriee;
    private ObservableList<CandidatGeneriqueTblModel>       listeCandidatGeneriqueStatuee = FXCollections
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
    private TableColumn<CandidatGeneriqueTblModel, Boolean> up;
    @FXML
    private TableColumn<CandidatGeneriqueTblModel, Boolean> down;

    public void setListeCandidatGenerique( ObservableList<CandidatGeneriqueTblModel> liste ) {
        listeCandidatGenerique = liste;
    }

    public ObservableList<CandidatGeneriqueTblModel> getListeClassee() {
        return listeCandidatGeneriqueAttenteTriee;
    }

    public ObservableList<CandidatGeneriqueTblModel> getListeStatuee() {
        return listeCandidatGeneriqueStatuee;
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        // compteurListeAttente = 0;
        initializeTable();
    }

    public void initializeTable() {
        id_Agent.setCellValueFactory( cellData -> cellData.getValue().getId_AgentProperty() );
        nom.setCellValueFactory( cellData -> cellData.getValue().getNomProperty() );
        matricule.setCellValueFactory( cellData -> cellData.getValue().getMatriculeProperty() );
        role.setCellValueFactory( cellData -> cellData.getValue().getRoleProperty() );
        statut.setCellValueFactory( cellData -> cellData.getValue().getStatutCandidatureProperty() );
        /*
         * statut.setCellValueFactory( new
         * Callback<TableColumn.CellDataFeatures<CandidatGeneriqueTblModel,
         * Number>, ObservableValue<Number>>() {
         * 
         * @Override public ObservableValue<Number> call(
         * TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Number> p ) {
         * return new SimpleIntegerProperty( compteurListeAttente++ ); } } );
         */

        up.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new SimpleBooleanProperty( p.getValue() != null );
                    }
                } );

        down.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new SimpleBooleanProperty( p.getValue() != null );
                    }
                } );

        // Definition des CellFactory
        up.setCellFactory(
                new Callback<TableColumn<CandidatGeneriqueTblModel, Boolean>, TableCell<CandidatGeneriqueTblModel, Boolean>>() {
                    @Override
                    public TableCell<CandidatGeneriqueTblModel, Boolean> call(
                            TableColumn<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new TableCell<CandidatGeneriqueTblModel, Boolean>() {
                            private Button button = new Button( "UP" );
                            {
                                button.setMaxWidth( 75.0 );
                                button.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle( ActionEvent t ) {
                                        final CandidatGeneriqueTblModel item = (CandidatGeneriqueTblModel) getTableRow()
                                                .getItem();
                                        int position = tableRowProperty().get().getIndex();
                                        if ( position > 0 ) {
                                            // Modification du statut de l'item
                                            // de la ligne
                                            item.setStatutCandidature( position );
                                            // Modification du statut de l'item
                                            // de la ligne superieure
                                            getTableView().getItems().get( position - 1 )
                                                    .setStatutCandidature( position + 1 );
                                        }
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

        // Definition des CellFactory
        down.setCellFactory(
                new Callback<TableColumn<CandidatGeneriqueTblModel, Boolean>, TableCell<CandidatGeneriqueTblModel, Boolean>>() {
                    @Override
                    public TableCell<CandidatGeneriqueTblModel, Boolean> call(
                            TableColumn<CandidatGeneriqueTblModel, Boolean> p ) {
                        return new TableCell<CandidatGeneriqueTblModel, Boolean>() {
                            private Button button = new Button( "DOWN" );
                            {
                                button.setMaxWidth( 75.0 );
                                button.setOnAction( new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle( ActionEvent t ) {
                                        final CandidatGeneriqueTblModel item = (CandidatGeneriqueTblModel) getTableRow()
                                                .getItem();
                                        int position = tableRowProperty().get().getIndex();
                                        if ( position < ( getTableView().getItems().size() - 1 ) ) {
                                            // Modification du statut de l'item
                                            // de la ligne
                                            item.setStatutCandidature( position + 2 );
                                            // Modification du statut de l'item
                                            // de la ligne superieure
                                            getTableView().getItems().get( position + 1 )
                                                    .setStatutCandidature( position + 1 );
                                        }
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
        int i = 1;
        try {
            for ( CandidatGeneriqueTblModel l : listeCandidatGenerique ) {
                if ( ( l.getStatutCandidature() > 0 ) || ( l.getStatutCandidature() < -1 ) ) {
                    l.setStatutCandidature( i );
                    i++;
                    listeCandidatGeneriqueAttente.add( l );
                } else {
                    listeCandidatGeneriqueStatuee.add( l );
                }
            }

            Callback<CandidatGeneriqueTblModel, Observable[]> cb = (
                    CandidatGeneriqueTblModel candidatGeneriqueTblModel ) -> new Observable[] {
                            candidatGeneriqueTblModel.getStatutCandidatureProperty(),
                    };

            listeCandidatGeneriqueAttenteTriee = FXCollections.observableArrayList( cb );
            listeCandidatGeneriqueAttenteTriee.addAll( listeCandidatGeneriqueAttente );

            SortedList<CandidatGeneriqueTblModel> sortedList = new SortedList<>( listeCandidatGeneriqueAttenteTriee,
                    ( CandidatGeneriqueTblModel c1, CandidatGeneriqueTblModel c2 ) -> {
                        if ( c1.getStatutCandidature() < c2.getStatutCandidature() ) {
                            return -1;
                        } else if ( c1.getStatutCandidature() > c2.getStatutCandidature() ) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } );
            candidatGeneriqueTable.setItems( sortedList );
        } catch ( RuntimeException e ) {
            DialogUtil.buildExceptionDialog( "Erreur", "Erreur de connexion", e )
                    .showAndWait();
            System.exit( -1 );
        }
    }
}
