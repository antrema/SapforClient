package application.utility;

import application.beans.CandidatGenerique;
import application.beans.CandidatGeneriqueTblModel;
import application.beans.CandidatureGenerique;
import application.beans.CandidatureGeneriqueTblModel;
import application.beans.SessionGenerique;
import application.beans.SessionGeneriqueTblModel;

public class Convert {

    public static SessionGeneriqueTblModel toSessionGeneriqueTblModel( SessionGenerique sessionGenerique ) {
        SessionGeneriqueTblModel sessionGeneriqueTblModel = new SessionGeneriqueTblModel();
        sessionGeneriqueTblModel.setId( sessionGenerique.getId() );
        sessionGeneriqueTblModel.setNom( sessionGenerique.getNom() );
        sessionGeneriqueTblModel.setDateDebut( sessionGenerique.getDateDebut() );
        sessionGeneriqueTblModel.setDateFin( sessionGenerique.getDateFin() );
        sessionGeneriqueTblModel.setUv( sessionGenerique.getUv() );
        sessionGeneriqueTblModel.setStage( sessionGenerique.getStage() );

        return sessionGeneriqueTblModel;
    }

    public static CandidatureGeneriqueTblModel toCandidatureGeneriqueTblModel(
            CandidatureGenerique candidatureGenerique ) {
        CandidatureGeneriqueTblModel candidatureGeneriqueTblModel = new CandidatureGeneriqueTblModel();
        candidatureGeneriqueTblModel.setId_Agent( candidatureGenerique.getId_Agent() );
        candidatureGeneriqueTblModel.setEstFormateur( candidatureGenerique.isEstFormateur() );
        candidatureGeneriqueTblModel.setStatutCandidature( candidatureGenerique.getStatutCandidature() );
        candidatureGeneriqueTblModel.setId_Session( candidatureGenerique.getId_Session() );
        candidatureGeneriqueTblModel.setNom( candidatureGenerique.getNom() );
        candidatureGeneriqueTblModel.setDateDebut( candidatureGenerique.getDateDebut() );
        candidatureGeneriqueTblModel.setDateFin( candidatureGenerique.getDateFin() );
        candidatureGeneriqueTblModel.setUv( candidatureGenerique.getUv() );
        candidatureGeneriqueTblModel.setStage( candidatureGenerique.getStage() );

        return candidatureGeneriqueTblModel;
    }

    /**
     * Convertit un objet CandidatGenerique en un objet
     * CandidatGeneriqueTblModel
     * 
     * @param candidatGenerique
     *            l'objet CandidatGenerique à convertir
     * @return l'objet converti au format CandidatGeneriqueTblModel
     */
    public static CandidatGeneriqueTblModel toCandidatGeneriqueTblModel(
            CandidatGenerique candidatGenerique ) {
        CandidatGeneriqueTblModel candidatGeneriqueTblModel = new CandidatGeneriqueTblModel();
        candidatGeneriqueTblModel.setId_Agent( candidatGenerique.getId_Agent() );
        candidatGeneriqueTblModel.setNom( candidatGenerique.getNom() );
        candidatGeneriqueTblModel.setMatricule( candidatGenerique.getMatricule() );
        candidatGeneriqueTblModel.setRole( candidatGenerique.getRole() );
        switch ( candidatGenerique.getStatutCandidature() ) {
        case -2:
            candidatGeneriqueTblModel.setStatutCandidature( candidatGenerique.getStatutCandidature() );
            candidatGeneriqueTblModel.setAccepte( false );
            candidatGeneriqueTblModel.setRefus( false );
            candidatGeneriqueTblModel.setListeAttente( false );
            break;
        case -1:
            candidatGeneriqueTblModel.setStatutCandidature( candidatGenerique.getStatutCandidature() );
            candidatGeneriqueTblModel.setAccepte( true );
            candidatGeneriqueTblModel.setRefus( false );
            candidatGeneriqueTblModel.setListeAttente( false );
            break;
        case 0:
            candidatGeneriqueTblModel.setStatutCandidature( candidatGenerique.getStatutCandidature() );
            candidatGeneriqueTblModel.setAccepte( false );
            candidatGeneriqueTblModel.setRefus( true );
            candidatGeneriqueTblModel.setListeAttente( false );
            break;
        default:
            candidatGeneriqueTblModel.setStatutCandidature( candidatGenerique.getStatutCandidature() );
            candidatGeneriqueTblModel.setAccepte( false );
            candidatGeneriqueTblModel.setRefus( false );
            candidatGeneriqueTblModel.setListeAttente( true );
            break;
        }

        return candidatGeneriqueTblModel;
    }

    /**
     * Convertit un objet CandidatGeneriqueTblModel en un objet
     * CandidatGenerique
     * 
     * @param candidatGeneriqueTblModel
     *            l'objet CandidatGeneriqueTblModel à convertir
     * @return l'objet converti au format CandidatGenerique
     */
    public static CandidatGenerique toCandidatGenerique(
            CandidatGeneriqueTblModel candidatGeneriqueTblModel ) {
        CandidatGenerique candidatGenerique = new CandidatGenerique();
        candidatGenerique.setId_Agent( candidatGeneriqueTblModel.getId_Agent() );
        candidatGenerique.setMatricule( candidatGeneriqueTblModel.getMatricule() );
        candidatGenerique.setNom( candidatGeneriqueTblModel.getNom() );
        candidatGenerique.setRole( candidatGeneriqueTblModel.getRole() );
        candidatGenerique.setStatutCandidature( candidatGeneriqueTblModel.getStatutCandidature() );

        return candidatGenerique;
    }
}
