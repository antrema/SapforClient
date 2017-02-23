package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SapforClient extends Application {

    private Stage      primaryStage;
    private BorderPane MainView;

    @Override
    public void start( Stage primaryStage ) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle( "Sapfor Client" );

        initApplicationView();

    }

    public void initApplicationView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation( SapforClient.class.getResource( "view/Main.fxml" ) );
            MainView = (BorderPane) loader.load();

            Scene scene = new Scene( MainView );
            primaryStage.setScene( scene );
            primaryStage.show();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        launch( args );
    }
}
