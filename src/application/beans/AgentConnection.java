package application.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AgentConnection {

    @XmlElement( name = "uuid" )
    private String uuid;

    @XmlElement( name = "nom" )
    private String nom;

    @XmlElement( name = "matricule" )
    private String matricule;

    /* Constructeurs */
    public AgentConnection() {
    }

    /* Accesseurs et Modificateurs */
    public String getUuid() {
        return uuid;
    }

    public void setUuid( String uuid ) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule( String matricule ) {
        this.matricule = matricule;
    }
}