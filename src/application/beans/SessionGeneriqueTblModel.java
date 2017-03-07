package application.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SessionGeneriqueTblModel {
    private final IntegerProperty id;
    private final StringProperty  nom;
    private final StringProperty  dateDebut;
    private final StringProperty  dateFin;
    private final StringProperty  uv;
    private final StringProperty  stage;

    public SessionGeneriqueTblModel() {
        this.id = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty();
        this.dateDebut = new SimpleStringProperty();
        this.dateFin = new SimpleStringProperty();
        this.uv = new SimpleStringProperty();
        this.stage = new SimpleStringProperty();
    }

    public SessionGeneriqueTblModel( Integer id, String nom, String dateDebut, String dateFin, String uv,
            String stage ) {
        this.id = new SimpleIntegerProperty( id );
        this.nom = new SimpleStringProperty( nom );
        this.dateDebut = new SimpleStringProperty( dateDebut );
        this.dateFin = new SimpleStringProperty( dateFin );
        this.uv = new SimpleStringProperty( uv );
        this.stage = new SimpleStringProperty( stage );
    }

    // ID
    public final Integer getId() {
        return id.get();
    }

    public final IntegerProperty getIdProperty() {
        return id;
    }

    public final void setId( Integer id ) {
        this.id.set( id );
    }

    // NOM
    public final String getNom() {
        return nom.get();
    }

    public final StringProperty getNomProperty() {
        return nom;
    }

    public final void setNom( String nom ) {
        this.nom.set( nom );
    }

    // DATE DEBUT
    public final String getDateDebut() {
        return dateDebut.get();
    }

    public final StringProperty getDateDebutProperty() {
        return dateDebut;
    }

    public final void setDateDebut( String dateDebut ) {
        this.dateDebut.set( dateDebut );
    }

    // DATE FIN
    public final String getDateFin() {
        return dateFin.get();
    }

    public final StringProperty getDateFinProperty() {
        return dateFin;
    }

    public final void setDateFin( String dateFin ) {
        this.dateFin.set( dateFin );
    }

    // UV
    public final String getUv() {
        return uv.get();
    }

    public final StringProperty getUvProperty() {
        return uv;
    }

    public final void setUv( String uv ) {
        this.uv.set( uv );
    }

    // STAGE
    public final String getStage() {
        return stage.get();
    }

    public final StringProperty getStageProperty() {
        return stage;
    }

    public final void setStage( String stage ) {
        this.stage.set( stage );
    }
}
