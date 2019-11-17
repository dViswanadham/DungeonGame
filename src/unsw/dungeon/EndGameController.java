/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 17/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class EndGameController {
    @FXML
    private Pane placeholder;

    @FXML
    private Button retry;
    
    @FXML
    void terminate(ActionEvent event) {
        
        currLevel.close();
    }

    private DungeonController master;
    private Stage currLevel;
    private String failureString;
    
    public EndGameController (DungeonController master, Stage currLevel, String failureString) {
        this.master = master;
        this.currLevel = currLevel;
        this.failureString = failureString;
    }
    
    @FXML
    public void initialize() {
        placeholder.getChildren().add(new Text(failureString));
        retry.setText("Retry");
        retry.setOnAction((event) -> {
            try {
                master.retryScreen(event);
                currLevel.close();
                
            } catch (FileNotFoundException fileNotFound) {
                
                fileNotFound.printStackTrace();
            }
        });
    }
}