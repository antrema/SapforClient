package application.beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CandidatGeneriqueTblModel {

    private final IntegerProperty id_Agent;
    private final StringProperty  nom;
    private final StringProperty  matricule;
    private final StringProperty  role;
    private final IntegerProperty statutCandidature;
    private final BooleanProperty accepte;
    private final BooleanProperty refus;
    private final BooleanProperty listeAttente;

    public CandidatGeneriqueTblModel() {
        this.id_Agent = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty();
        this.matricule = new SimpleStringProperty();
        this.role = new SimpleStringProperty();
        this.statutCandidature = new SimpleIntegerProperty();
        this.accepte = new SimpleBooleanProperty( false );
        this.refus = new SimpleBooleanProperty( false );
        this.listeAttente = new SimpleBooleanProperty( false );
    }

    public CandidatGeneriqueTblModel( Integer id_Agent, String nom, String matricule, String role,
            Integer statutCandidature, Boolean accepte, Boolean refus, Boolean attente ) {
        this.id_Agent = new SimpleIntegerProperty( id_Agent );
        this.nom = new SimpleStringProperty( nom );
        this.matricule = new SimpleStringProperty( matricule );
        this.role = new SimpleStringProperty( role );
        this.statutCandidature = new SimpleIntegerProperty( statutCandidature );
        this.accepte = new SimpleBooleanProperty( accepte );
        this.refus = new SimpleBooleanProperty( refus );
        this.listeAttente = new SimpleBooleanProperty( attente );
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

    // MATRICULE
    public final String getMatricule() {
        return matricule.get();
    }

    public final StringProperty getMatriculeProperty() {
        return matricule;
    }

    public final void setMatricule( String matricule ) {
        this.matricule.set( matricule );
    }

    // ESTFORMATEUR
    public final String getRole() {
        return role.get();
    }

    public final StringProperty getRoleProperty() {
        return role;
    }

    public final void setRole( String role ) {
        this.role.set( role );
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

    // ACCEPTE
    public final Boolean getAccepte() {
        return accepte.get();
    }

    public final BooleanProperty getAccepteProperty() {
        return accepte;
    }

    public final void setAccepte( Boolean accepte ) {
        this.accepte.set( accepte );
    }

    // REFUS
    public final Boolean getRefus() {
        return refus.get();
    }

    public final BooleanProperty getRefusProperty() {
        return refus;
    }

    public final void setRefus( Boolean refus ) {
        this.refus.set( refus );
    }

    // ATTENTE
    public final Boolean getListeAttente() {
        return listeAttente.get();
    }

    public final BooleanProperty getListeAttenteProperty() {
        return listeAttente;
    }

    public final void setListeAttente( Boolean attente ) {
        this.listeAttente.set( attente );
    }
}
