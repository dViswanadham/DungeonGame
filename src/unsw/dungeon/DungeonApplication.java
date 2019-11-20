/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 20/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DungeonApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        List<String> dungeons = new ArrayList<>();
        
        File fl = new File("dungeons");
        File[] list = fl.listFiles();
        
        
        for(File f : list) {
            if (f.isFile()) {
                
                dungeons.add(f.getName());
            }
        }
        
        DungeonController controller = new DungeonController(dungeons);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        
        loader.setController(controller);
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        controller.determineLevel(primaryStage);
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}