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
import java.util.ArrayList;

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

        for(int i = 0; i < jsonEntities.length(); i++) {
            
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        dungeon.linkObsBoulder();
        dungeon.alertObsBoulders();
        dungeon.linkObsExit();
        dungeon.bootFlag();
        
        JSONObject objectives = json.getJSONObject("goal-condition");
        
        // Goals conditions = dungeonObjectives(objectives, dungeon);
        // dungeon.createAim(conditions);
        
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id = 0;

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
        case "portal":
        	id = json.getInt("id");
    		Portal portal = new Portal(x, y, dungeon, id);
    		onLoad(portal);
    		entity = portal;
    		break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(x, y, dungeon, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(x, y, dungeon, id);
        	onLoad(key);
        	entity = key;
        	break;
        case "default":
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
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Key key);

//    private Goals dungeonObjectives(JSONObject aims, Dungeon dungeon){
//        String type = aims.getString("goal");
//        
//        Goals conditions = null;
//        
//        ComplexObjectives complex = null;
//        switch(type) {
//            case "AND":
//            	complex = new ComplexObjectives(type);
//            	JSONArray andminiGoals = aims.getJSONArray("subgoals");
//            	
//            	for(int a = 0; a < andminiGoals.length(); a = (a + 1)) {
//            		Goals miniGoal = dungeonObjectives(andminiGoals.getJSONObject(a), dungeon);
//            		complex.append(miniGoal);
//            		
//            		loadGoal(dungeon, andminiGoals.getJSONObject(a));
//            	}
//            	
//            	conditions = complex;
//            	break;
//            	
//            case "OR":
//                complex = new ComplexObjectives(type);
//                JSONArray minigoals = aims.getJSONArray("subgoals");
//                
//                for(int a = 0; a < minigoals.length(); a = (a + 1)) {
//                    Goals miniGoal = dungeonObjectives(minigoals.getJSONObject(a), dungeon);
//                    complex.append(miniGoal);
//                    
//                    loadGoal(dungeon, minigoals.getJSONObject(a));
//                }
//                
//                conditions = complex;
//                break;
//                
//            default:
//                break;
//        }
//
//        return conditions;
//    }
//    
//    public void loadGoal(Dungeon dungeon, JSONObject json) {
//    	String type = json.getString("goal");
//    	
//    	switch(type) {
//	        case "exit":
//	            ExitGoal exitGoal = new ExitGoal("exit");
////	            conditions = exitGoal;
//	            observeObjective(exitGoal, dungeon);
//	            break;
//	            
//	        case "enemies":
//	            EnemyGoal enemyGoal = new EnemyGoal("enemies");
////	            conditions = enemyGoal;
//	            observeObjective(enemyGoal, dungeon);
//	            break;
//	            
//	        case "treasure":
//	            TreasureGoal treasureGoal = new TreasureGoal("treasure");
////	            conditions = treasureGoal;
//	            observeObjective(treasureGoal, dungeon);
//	            break;
//	            
//	        case "boulders":
//	            SwitchGoal boulderGoal = new SwitchGoal("boulders");
////	            conditions = boulderGoal;
//	            observeObjective(boulderGoal, dungeon);
//	            break;
//	    }
//    }
    
//    private Goals dungeonObjectives(JSONObject jsonAims, Dungeon dungeon){
//        String type = jsonAims.getString("goal");
//        
//        Goals conditions = null;
//        
//        switch(type) {
//            case "AND":
//                break;
//                
//            case "OR":
//                ComplexObjectives complex = new ComplexObjectives(type);
//                JSONArray minigoals = jsonAims.getJSONArray("subgoals");
//                
//                for(int a = 0; a < minigoals.length(); a = (a + 1)) {
//                    Goals miniGoal = dungeonObjectives(minigoals.getJSONObject(a), dungeon);
//                    
//                    complex.append(miniGoal);
//                }
//                
//                conditions = complex;
//                break;
//                
//            case "exit":
//                ExitGoal exitGoal = new ExitGoal("exit");
//                conditions = exitGoal;
//                observeObjective(exitGoal, dungeon);
//                break;
//                
//            case "enemies":
//                EnemyGoal enemyGoal = new EnemyGoal("enemies");
//                conditions = enemyGoal;
//                observeObjective(enemyGoal, dungeon);
//                break;
//                
//            case "treasure":
//                TreasureGoal treasureGoal = new TreasureGoal("treasure");
//                conditions = treasureGoal;
//                observeObjective(treasureGoal, dungeon);
//                break;
//                
//            case "boulders":
//                SwitchGoal boulderGoal = new SwitchGoal("boulders");
//                conditions = boulderGoal;
//                observeObjective(boulderGoal, dungeon);
//                break;
//                
//            default:
//                break;
//        }
//
//        return conditions;
//    }
    

    
    /**
     * 
     * Function observes the Exit goal, so we know wehn it is complete.
     * 
     * @param aim (exit)
     * @param dungeon
     */
    private void observeObjective(ExitGoal aim, Dungeon dungeon) {
        Exit e = dungeon.obtainExit();
        
        if (e == null) {
            
            throw new UnsupportedOperationException();
        }
        
        e.registerObserver(aim);
    }
    
    /**
     * 
     * Function observes each enemy so that we know wehn the Enemy goal is complete.
     * 
     * @param aim (enemy)
     * @param dungeon
     */
    private void observeObjective(EnemyGoal aim, Dungeon dungeon) {
        ArrayList<Enemy> total_threats = dungeon.obtainEnemies();
        
        if (total_threats.size() == 0) {
            
            throw new UnsupportedOperationException();
        }
        
        for(Enemy single_threat : total_threats) {
            System.out.println("Threat registered.");
            
            single_threat.registerObserver(aim);
        }
    }
    
    /**
     * 
     * Function observes each "coin" so that we know when the Treasure goal is complete.
     * 
     * @param aim (treasure)
     * @param dungeon
     */
    private void observeObjective(TreasureGoal aim, Dungeon dungeon) {
        ArrayList<Treasure> loot = dungeon.obtainTres();
        
        if (loot.size() == 0) {
            
            throw new UnsupportedOperationException();
        }
        
        for(Treasure single_coin : loot) {
            
            single_coin.registerObserver(aim);
        }
    }
    
    /**
     * 
     * Function observes each switch so we know when the Switch goal is complete.
     * 
     * @param aim (floor switch)
     * @param dungeon
     */
    private void observeObjective(SwitchGoal aim, Dungeon dungeon) {
        ArrayList<Switch> switches = dungeon.obtainSwitch();
        
        if (switches.size() == 0) {
            
            throw new UnsupportedOperationException();
        }
        
        for(Switch floor_switch : switches) {
            
            floor_switch.registerObserver(aim);
        }
    }
}