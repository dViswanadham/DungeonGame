/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 17/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class SuccessScreenHandler {
    public SuccessScreenHandler(DungeonController controller, String text) throws IOException {
        final Stage show = new Stage();
        
        FXMLLoader deploy = new FXMLLoader(getClass().getResource("SuccessScreen.fxml"));
        
        SuccessScreenController controlling = new SuccessScreenController(controller, show, text);
        
        deploy.setController(controlling);
        
        Parent root = deploy.load();
        Scene scene = new Scene(root);
        
        show.setScene(scene);
        show.show();
    }
}