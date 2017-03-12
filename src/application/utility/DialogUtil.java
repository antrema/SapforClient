package application.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogUtil {

    public static Alert buildSimpleDialog( String message ) {
        Alert alert = new Alert( AlertType.INFORMATION );
        alert.setTitle( "Information" );
        alert.setHeaderText( null );
        alert.setContentText( message );
        return alert;
    }

    public static Alert buildExceptionDialog( String message, Exception e ) {
        Alert alert = new Alert( AlertType.ERROR );
        alert.setTitle( "Exception" );
        alert.setHeaderText( null );
        alert.setContentText( message );
        e.printStackTrace();
        return alert;
    }
}
