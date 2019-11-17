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
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;


public class SuccessScreenController {
    @FXML
    private Pane placeholder;
    
    private DungeonController controller;
    private Stage currLevel;
    private String successString;
    
    public SuccessScreenController (DungeonController controller, Stage currLevel, String successString) {
        this.controller = controller;
        this.currLevel = currLevel;
        this.successString = successString;
    }
    
    @FXML
    public void initialize() {
        
        placeholder.getChildren().add(new Text(successString));
    }
    
    @FXML
    void terminate(ActionEvent event) {
        
        currLevel.close();
    }
    
    @FXML
    void masterMenu(ActionEvent event) {
        controller.menuScreen(event);
        
        currLevel.close();
    }
    
    @FXML
    void freshDungeon(ActionEvent event) throws FileNotFoundException {
        controller.chooseRandom();
        
        currLevel.close();
    }
}