package application.utility;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import application.beans.AgentConnection;
import application.controller.MainController;

public class RESTClient {
    private static final String WS_URI = "http://localhost:8080/Sapfor/rest";

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

    public static boolean getIdentification( String user, String pw ) {
        Client client = null;
        try {
            // Connexion au serveur REST
            HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic( user, pw );
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.register( feature );

            client = ClientBuilder.newClient( clientConfig );
            WebTarget target = client.target( getBaseUri() );

            Response response = target.path( "agent/connexion" ).request().get();
            MultivaluedMap<String, String> headerssss = response.getStringHeaders();
            System.out.println( response.getStatus() );

            if ( response.getStatus() != 200 ) {
                return false;
            } else {
                AgentConnection agentConnection = response.readEntity( AgentConnection.class );
                MainController.setAg( agentConnection );
                return true;
            }
        } catch ( RuntimeException e ) {
            e.printStackTrace();
            return false;
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
            // do nothing
        }
        return stateOfWS;
    }

    private static URI getBaseUri() {
        return UriBuilder.fromUri( WS_URI ).build();
    }
}
