package application.utility;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import application.beans.AgentConnection;
import application.beans.CandidatGenerique;
import application.beans.CandidatGeneriqueTblModel;
import application.beans.CandidatureGenerique;
import application.beans.CandidatureGeneriqueTblModel;
import application.beans.SessionGenerique;
import application.beans.SessionGeneriqueTblModel;

public class RESTClient {
    private static final String WS_URI    = "http://localhost:8080/Sapfor/rest";
    private static String       matricule = "";
    private static String       nom       = "";
    private static String       uuid      = "";
    private static boolean      gestionnaire;
    private static boolean      auth;

    public static String getMatricule() {
        return matricule;
    }

    public static String getNom() {
        return nom;
    }

    public static String getUuid() {
        return uuid;
    }

    public static boolean getGestionnaire() {
        return gestionnaire;
    }

    public static boolean getAuth() {
        return auth;
    }

    public static List<CandidatureGeneriqueTblModel> findCandidature() {
        Client client = null;
        try {
            List<CandidatureGeneriqueTblModel> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target( getBaseUri() );
            List<CandidatureGenerique> accessible = target.path( "session/" + uuid ).request()
                    .get( new GenericType<List<CandidatureGenerique>>() {
                    } ); // get all users
            accessible.stream().forEach( ( user ) -> {
                models.add( Convert.toCandidatureGeneriqueTblModel( user ) );
            } );
            return models;
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if ( client != null )
                client.close();
        }
    }

    public static List<CandidatureGeneriqueTblModel> findAccessibleSessions() {
        Client client = null;
        try {
            List<CandidatureGeneriqueTblModel> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target( getBaseUri() );
            List<CandidatureGenerique> accessible = target.path( "session/" + uuid + "/accessible" ).request()
                    .get( new GenericType<List<CandidatureGenerique>>() {
                    } ); // get all users
            accessible.stream().forEach( ( user ) -> {
                models.add( Convert.toCandidatureGeneriqueTblModel( user ) );
            } );
            return models;
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if ( client != null )
                client.close();
        }
    }

    public static boolean Candidater( String session, String formateur ) {
        Client client = null;
        client = ClientBuilder.newClient();
        WebTarget target = client.target( getBaseUri() );
        int status = target.path( "session/" + uuid + "/candidater" )
                .queryParam( "Session", session )
                .queryParam( "Formateur", formateur )
                .request().get().getStatus();
        return ( status == 200 ? true : false );
    }

    public static boolean RetraitCandidature( String session ) {
        Client client = null;
        client = ClientBuilder.newClient();
        WebTarget target = client.target( getBaseUri() );
        int status = target.path( "session/" + uuid + "/retirerCandidature" )
                .queryParam( "Session", session )
                .request().get().getStatus();
        return ( status == 200 ? true : false );
    }

    /**
     * Genere la liste des Candidat a une session donnee
     * 
     * @param idSession
     *            Id de la session concerne
     * @return Liste des Candidat aynt candidate
     */
    public static List<CandidatGeneriqueTblModel> findCandidat( int idSession ) {
        Client client = null;
        try {
            List<CandidatGeneriqueTblModel> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target( getBaseUri() );
            List<CandidatGenerique> all = target.path( "session/" + uuid + "/listeCandidat" )
                    .queryParam( "Session", idSession ).request()
                    .get( new GenericType<List<CandidatGenerique>>() {
                    } ); // get all users
            all.stream().forEach( ( user ) -> {
                models.add( Convert.toCandidatGeneriqueTblModel( user ) );
            } );
            return models;
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if ( client != null )
                client.close();
        }
    }

    public static List<SessionGeneriqueTblModel> findAllSessions() {
        Client client = null;
        try {
            List<SessionGeneriqueTblModel> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target( getBaseUri() );
            List<SessionGenerique> all = target.path( "session" ).request()

                    .get( new GenericType<List<SessionGenerique>>() {
                    } ); // get all users
            all.stream().forEach( ( user ) -> {
                models.add( Convert.toSessionGeneriqueTblModel( user ) );
            } );
            return models;
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if ( client != null )
                client.close();
        }
    }

    public static List<SessionGeneriqueTblModel> findAllClosedSessions() {
        Client client = null;
        try {
            List<SessionGeneriqueTblModel> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target( getBaseUri() );
            List<SessionGenerique> all = target.path( "session/listeFermees" ).request()

                    .get( new GenericType<List<SessionGenerique>>() {
                    } ); // get all list
            all.stream().forEach( ( user ) -> {
                models.add( Convert.toSessionGeneriqueTblModel( user ) );
            } );
            return models;
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if ( client != null )
                client.close();
        }
    }

    public static List<SessionGeneriqueTblModel> findAllOpenedSessions() {
        Client client = null;
        try {
            List<SessionGeneriqueTblModel> models = new ArrayList<>();
            client = ClientBuilder.newClient();
            WebTarget target = client.target( getBaseUri() );
            List<SessionGenerique> all = target.path( "session/listeOuvertes" ).request()

                    .get( new GenericType<List<SessionGenerique>>() {
                    } ); // get all list
            all.stream().forEach( ( user ) -> {
                models.add( Convert.toSessionGeneriqueTblModel( user ) );
            } );
            return models;
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if ( client != null )
                client.close();
        }
    }

    public static void setListeCandidats( List<CandidatGeneriqueTblModel> listeCandidatsTbl, int idSession ) {
        Client client = null;
        List<CandidatGenerique> listeCandidats = new ArrayList<CandidatGenerique>();
        for ( CandidatGeneriqueTblModel c : listeCandidatsTbl ) {
            listeCandidats.add( Convert.toCandidatGenerique( c ) );
        }
        GenericEntity<List<CandidatGenerique>> listeCandidatEntity = new GenericEntity<List<CandidatGenerique>>(
                listeCandidats ) {
        };

        client = ClientBuilder.newClient();
        WebTarget target = client.target( getBaseUri() ).path( "session/" + uuid + "/modifierCandidats" )
                .queryParam( "Session", idSession );

        Invocation.Builder invocationBuilder = target.request( MediaType.APPLICATION_XML );
        Response response = invocationBuilder.post( Entity.xml( listeCandidatEntity ) );
    }

    public static boolean  fermerCandidature( String session) {
        Client client = null;
        client = ClientBuilder.newClient();
        WebTarget target = client.target( getBaseUri() );
        int status = target.path( "session/" + uuid + "/fermerCandidature" )
                .queryParam( "Session", session )
                .request().get().getStatus();
        return ( status == 200 ? true : false );
    }
    
    public static boolean  ouvrirCandidature( String session) {
        Client client = null;
        client = ClientBuilder.newClient();
        WebTarget target = client.target( getBaseUri() );
        int status = target.path( "session/" + uuid + "/ouvrirCandidature" )
                .queryParam( "Session", session )
                .request().get().getStatus();
        return ( status == 200 ? true : false );
    }

    public static AgentConnection getIdentification( String user, String pw ) {
        Client client = null;
        try {
            // Connexion au serveur REST
            HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic( user, pw );
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.register( feature );

            client = ClientBuilder.newClient( clientConfig );
            WebTarget target = client.target( getBaseUri() );

            Response response = target.path( "agent/connexion" ).request().get();
            System.out.println( response.getStatus() );

            if ( response.getStatus() != 200 ) {
                return null;
            } else {
                AgentConnection agentConnection = response.readEntity( AgentConnection.class );
                matricule = agentConnection.getMatricule();
                uuid = agentConnection.getUuid();
                nom = agentConnection.getNom();

                return agentConnection;
            }
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * ************************************* UTIL METHODS
     ***************************************/
    // Check the status of RESTful WebService
    public static boolean checkWS() {
        Boolean stateOfWS = false;
        try {
            URL siteURL = new URL( getBaseUri().toString() );
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod( "GET" );
            connection.connect();
            int code = connection.getResponseCode();
            if ( code == 200 )
                stateOfWS = true;
        } catch ( Exception e ) {
        }
        return stateOfWS;
    }

    private static URI getBaseUri() {
        return UriBuilder.fromUri( WS_URI ).build();
    }
}
