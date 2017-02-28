package application.utility;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CandidatureGeneriqueTblModel {
    private final IntegerProperty id_Agent;
    private final BooleanProperty estFormateur;
    private final IntegerProperty statutCandidature;
    private final IntegerProperty id_Session;
    private final StringProperty  nom;
    private final StringProperty  dateDebut;
    private final StringProperty  dateFin;
    private final StringProperty  uv;
    private final StringProperty  stage;

    public CandidatureGeneriqueTblModel() {
        this.id_Agent = new SimpleIntegerProperty();
        this.estFormateur = new SimpleBooleanProperty();
        this.statutCandidature = new SimpleIntegerProperty();
        this.id_Session = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty();
        this.dateDebut = new SimpleStringProperty();
        this.dateFin = new SimpleStringProperty();
        this.uv = new SimpleStringProperty();
        this.stage = new SimpleStringProperty();
    }

    public CandidatureGeneriqueTblModel( Integer id_Agent, Boolean estFormateur, Integer statutCandidature,
            Integer id_Session, String nom, String dateDebut, String dateFin, String uv,
            String stage ) {
        this.id_Agent = new SimpleIntegerProperty( id_Agent );
        this.estFormateur = new SimpleBooleanProperty( estFormateur );
        this.statutCandidature = new SimpleIntegerProperty( statutCandidature );
        this.id_Session = new SimpleIntegerProperty( id_Session );
        this.nom = new SimpleStringProperty( nom );
        this.dateDebut = new SimpleStringProperty( dateDebut );
        this.dateFin = new SimpleStringProperty( dateFin );
        this.uv = new SimpleStringProperty( uv );
        this.stage = new SimpleStringProperty( stage );
    }

    // ID_AGENT
    public final Integer getId_Agent() {
        return id_Agent.get();
    }

    public final IntegerProperty getId_AgentProperty() {
        return id_Agent;
    }

    public final void setId_Agent( Integer id ) {
        this.id_Agent.set( id );
    }

    // ESTFORMATEUR
    public final Boolean getEstFormateur() {
        return estFormateur.get();
    }

    public final BooleanProperty getEstFormateurProperty() {
        return estFormateur;
    }

    public final void setEstFormateur( Boolean formateur ) {
        this.estFormateur.set( formateur );
    }

    // STATUTCANDIDATURE
    public final Integer getStatutCandidature() {
        return statutCandidature.get();
    }

    public final IntegerProperty getStatutCandidatureProperty() {
        return statutCandidature;
    }

    public final void setStatutCandidature( Integer statut ) {
        this.statutCandidature.set( statut );
    }

    // ID_SESSION
    public final Integer getId_Session() {
        return id_Session.get();
    }

    public final IntegerProperty getId_SessionProperty() {
        return id_Session;
    }

    public final void setId_Session( Integer id ) {
        this.id_Session.set( id );
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
