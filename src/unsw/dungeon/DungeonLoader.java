/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.ImageView;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, dungeon);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y, dungeon);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y, dungeon);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch floorswitch = new Switch(x, y, dungeon);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y, dungeon);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "invincibility":
        	InvincibilityPotion potion = new InvincibilityPotion(x, y, dungeon);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, dungeon);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch floorswitch);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(InvincibilityPotion potion);
    
    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Enemy enemy);

}
