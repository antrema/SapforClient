package application.beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AgentConnectionProperty {

    private StringProperty  uuid         = new SimpleStringProperty( this, "uuid" );
    private StringProperty  nom          = new SimpleStringProperty( this, "nom" );
    private StringProperty  matricule    = new SimpleStringProperty( this, "matricule" );
    private BooleanProperty gestionnaire = new SimpleBooleanProperty( this, "gestionnaire" );

    /* Accesseurs et Modificateurs */
    // UUID
    public final StringProperty uuidProperty() {
        return uuid;
    }

    public final String getUuid() {
        return uuid.get();
    }

    public final void setUuid( String u ) {
        uuid.set( u );
    }

    // NOM
    public final StringProperty nomProperty() {
        return nom;
    }

    public final String getNom() {
        return nom.get();
    }

    public final void setNom( String n ) {
        nom.set( n );
    }

    // MATRICULE
    public final StringProperty matriculeProperty() {
        return matricule;
    }

    public final String getMatricule() {
        return matricule.get();
    }

    public final void setMatricule( String m ) {
        matricule.set( m );
    }

    // GESTIONNAIRE
    public final BooleanProperty gestionnaireProperty() {
        return gestionnaire;
    }

    public final boolean getGestionnaire() {
        return gestionnaire.get();
    }

    public final void setGestionnaire( boolean g ) {
        gestionnaire.set( g );
    }

}