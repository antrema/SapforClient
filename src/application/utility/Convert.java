package application.utility;

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
}
