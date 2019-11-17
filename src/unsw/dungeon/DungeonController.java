/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 17/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private Pane splitScreen;

    @FXML
    private GridPane grid;

    @FXML
    private MenuBar menuArea;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem retry;

    @FXML
    private MenuItem quit;

    @FXML
    private Menu levelSelect;

    @FXML
    private Pane txt1Pane;
    
    @FXML
    private Pane txt2Pane;
    
    @FXML
    private Pane area1Pane;
    
    @FXML
    private Pane area2Pane;
    
    @FXML
    private Pane centralPane;
    
    private Stage level = null;
    private List<String> dungeonLevels;
    private Dungeon currDungeon = null;
    private String currDungeonLoc = null;
    private ChoiceBox<String> dungeonSelect = null;
    private ChoiceBox<String> difficultySelect = null;
    
    private int totalObjectives;
    private boolean inputEnabled;
    
    private List<ImageView> initialEntities;
    
    private HashMap<Entity, ImageView> entityMap = new HashMap<>();

    private Player player;
    
    private Dungeon dungeon;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        // Determines dungeon difficulty
        txt2Pane.getChildren().add(new Text("Choose Your Difficulty"));
        difficultySelect = new ChoiceBox<String>();
        difficultySelect.getItems().addAll("Explorer", "Hero", "Slayer");
        difficultySelect.setValue("Slayer");
        area2Pane.getChildren().add(difficultySelect);
        
        // Determine dungeon mode
        txt1Pane.getChildren().add(new Text("Choose Your Quest"));
        dungeonSelect = new ChoiceBox<String>();
        area1Pane.getChildren().add(dungeonSelect);
        
        for(int a = 0; a < dungeonLevels.size(); a = (a + 1)) {
            String title = dungeonLevels.get(a);
            String titleLoc = title.replaceAll(".json$", "");
            
            MenuItem loc = new MenuItem(titleLoc);
            
            loc.setOnAction((event) -> {
                // Deploy the requested dungeon
                try {
                    activateDungeon(title);
                    
                } catch (FileNotFoundException notFound) {
                    
                    notFound.printStackTrace();
                }
            });
            
            levelSelect.getItems().add(loc);
            dungeonSelect.getItems().add(a, titleLoc);
        }
        
//        Image ground = new Image("/dirt_0_new.png");
//        
//        // Add the ground first so it is below all other entities
//        for (int x = 0; x < dungeon.getWidth(); x++) {
//            for (int y = 0; y < dungeon.getHeight(); y++) {
//                grid.add(new ImageView(ground), x, y);
//            }
//        }
        List<Entity> entities = dungeon.getEntityList();
        Iterator<Entity> it1 = entities.iterator();
        Iterator<ImageView> it2 = initialEntities.iterator();
        
        while(it1.hasNext() && it2.hasNext()) {
        	ImageView entity = it2.next();
        	entityMap.put(it1.next(), entity);
        	grid.getChildren().add(entity);
        } 
        
        for(Entity e : entities) {
        	e.seeable().addListener(new ChangeListener<Boolean>() {
        	    
        	@Override
        	public void changed(ObservableValue<? extends Boolean> observable, 
        	                    Boolean old, Boolean newV) {
        	    
        		removeEntityImage(e);
        	}
        });
        }
    }
    
    public void removeEntityImage(Entity entity) {
    	ImageView view = entityMap.get(entity);
    	grid.getChildren().remove(view);
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.move(Direction.UP);
            break;
        case DOWN:
            player.move(Direction.DOWN);
            break;
        case LEFT:
            player.move(Direction.LEFT);
            break;
        case RIGHT:
            player.move(Direction.RIGHT);
            break;
        case R:
            // Key restarts dungeon
            try {
                dungeonRetry();
                
            } catch (FileNotFoundException notFound1) {
                
                notFound1.printStackTrace();
            }
            break;
        default:
            break;
        }
    }
    
    @FXML
    void menuScreen(ActionEvent event) {
        
        showMenuScreen();
    }
    
    @FXML
    void quitScreen(ActionEvent event) {
        
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    void selectDungeonScreen(ActionEvent event) {
        int curr = dungeonSelect.getSelectionModel().getSelectedIndex();
        
        if (curr == -1 || curr >= dungeonLevels.size()) {
            
            return;
        }
        
        try {
            activateDungeon(dungeonLevels.get(curr));
            
        } catch (FileNotFoundException fileNotFound) {
            
            fileNotFound.printStackTrace();
        }
    }
    
    @FXML
    void retryScreen(ActionEvent event) throws FileNotFoundException {
        
        dungeonRetry();
    }
    
    private void dungeonRetry() throws FileNotFoundException {
        String prev = this.currDungeonLoc;
        
        if (prev == null) {
            
            return;
        }
        
        activateDungeon(prev);
    }
    
    /**
     * Function deploys the dungeon and relevant goals.
     * It also attaches listeners to track the progress of the player and goals
     * allowing the player to know when the game is completed.
     * 
     * @param loc
     * @throws FileNotFoundException
     */
    private void activateDungeon(String loc) throws FileNotFoundException {
        showMenuScreen();
        
        DungeonControllerLoader deployDungeon = new DungeonControllerLoader(loc);
        
        this.currDungeon = deployDungeon.load();
        this.currDungeonLoc = loc;
        
        Dungeon currentLevel = this.currDungeon;
        
        List<ImageView> startingEntities = deployDungeon.obtainEntities();
        
        this.totalObjectives = 0;
        
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for(int x_coord = 0; x_coord < currentLevel.getWidth(); x_coord = (x_coord + 1)) {
            for(int y_coord = 0; y_coord < currentLevel.getHeight(); y_coord = (y_coord + 1)) {
                
                grid.add(new ImageView(ground), x_coord, y_coord);
            }
        }

        for(ImageView e : startingEntities) {
            
            grid.getChildren().add(e);
        }
        
        Goals obj = currentLevel.obtainAim();
        
        if (obj instanceof ComplexObjectives) {
            for(Goals aim : ((ComplexObjectives) obj).obtainObjectives()) {
                showObjectives(aim);
                
                this.totalObjectives += 2;
            }
            
        } else {
            
            showObjectives(obj);
        }
                
        this.level.setWidth((currentLevel.getWidth() + 1) * 32 + 16);
        this.level.setHeight((currentLevel.getHeight() + 3) * 32);
        this.inputEnabled = true;
        
        grid.requestFocus();
        
        Player pl = currentLevel.getPlayer();
        
        pl.getActiveProperty().addListener(new ChangeListener<Boolean>() {
            
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                                Boolean old, Boolean newV) {
                
                EndGame("Failure! You have Died!");
            }
        });
        
        Goals dungeonAim = currDungeon.obtainAim();
        
        dungeonAim.completedObj().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                                Boolean old, Boolean newV) {
                
                Success("You have successfully finished!");
                deactivateKeyControls();
            }
        });
    }
    
    /**
     * Function shows relevant goals to be completed.
     * 
     * @param obj
     */
    private void showObjectives(Goals obj) {
        if (obj instanceof TreasureGoal) {
            Image loot = new Image("/gold_pile.png");
            Label label1 = new Label();
            
            label1.setMaxWidth(Double.MAX_VALUE);
            label1.textProperty().bind(Bindings.concat(obj.obtainCheckpoint().asString(), "/", obj.obtainCombined().asString()));
            
            this.grid.add(new ImageView(loot), this.totalObjectives, this.currDungeon.getHeight());
            this.grid.add(label1, this.totalObjectives + 1, this.currDungeon.getHeight());
            this.grid.setFillWidth(label1, true);

        } else if (obj instanceof EnemyGoal) {
            Image threat = new Image("/deep_elf_master_archer.png");
            Label label2 = new Label();
            
            label2.setMaxWidth(Double.MAX_VALUE);
            label2.textProperty().bind(Bindings.concat(obj.obtainCheckpoint().asString(), "/", obj.obtainCombined().asString()));
            
            this.grid.add(new ImageView(threat), this.totalObjectives, this.currDungeon.getHeight());
            this.grid.add(label2, this.totalObjectives+1, this.currDungeon.getHeight());
            this.grid.setFillWidth(label2, true);
            
        } else if (obj instanceof SwitchGoal) {
            Image b = new Image("/boulder.png");
            Label label3 = new Label();
            
            label3.setMaxWidth(Double.MAX_VALUE);
            label3.textProperty().bind(Bindings.concat(obj.obtainCheckpoint().asString(), "/", obj.obtainCombined().asString()));
            
            this.grid.add(new ImageView(b), this.totalObjectives, this.currDungeon.getHeight());
            this.grid.add(label3, this.totalObjectives+1, this.currDungeon.getHeight());
            this.grid.setFillWidth(label3, true);
            
        } else if (obj instanceof ExitGoal) {
            Image ex = new Image("/exit.png");
            Label label4 = new Label();
            
            label4.setMaxWidth(Double.MAX_VALUE);
            label4.textProperty().bind(Bindings.concat("Last"));
            
            this.grid.add(new ImageView(ex), this.totalObjectives, this.currDungeon.getHeight());
            this.grid.add(label4, this.totalObjectives+1, this.currDungeon.getHeight());
            this.grid.setFillWidth(label4, true);
        }
    }
    
    /**
     * Function shows endgame screen.
     * 
     * @param txt
     */
    private void EndGame(String txt) {
        try {
            new EndGameHandler(this, txt);
            
        } catch (IOException io) {
            
            io.printStackTrace();
        }
    }
    
    /**
     * Function shows success screen
     * 
     * @param txt
     */
    private void Success(String txt) {
        try {
            new SuccessScreenHandler(this, txt);
            
        } catch (IOException io) {
            
            io.printStackTrace();
        }
    }
    
    /**
     * Function shows the menu screen
     */
    private void showMenuScreen() {
        this.currDungeon = null;
        this.currDungeonLoc = null;
        
        grid.getChildren().clear();
        
        this.totalObjectives = 0;
        this.level.setWidth(400 + 16);
        this.level.setHeight(300 + 32 + 7);
    }
    
    /**
     * Function makes the level for the dungeon as required by the controller.
     * 
     * @param level
     */
    public void determineLevel(Stage level) {
        
        this.level = level;
    }
    
    /**
     * 
     * Function obtains the current deployable dungeon
     * 
     * @return Dungeon
     */
    public Dungeon obtainDungeon() {
        
        return this.currDungeon;
    }
    
    /**
     * 
     * Function generates the dungeon randomly
     * 
     * @throws FileNotFoundException
     */
    public void chooseRandom() throws FileNotFoundException {
        Random any = new Random();
        
        int chosen_one = any.nextInt(dungeonLevels.size());
        System.out.println(chosen_one);
        
        activateDungeon(dungeonLevels.get(chosen_one));
    }
    
    /**
     * Function deactivates user input in dungeon after death
     */
    private void deactivateKeyControls() {
        
        this.inputEnabled = false;
    }
}