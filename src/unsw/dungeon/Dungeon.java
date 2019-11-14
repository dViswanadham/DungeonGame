/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private boolean status;
    private Inventory inventory;
    private boolean endGame;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.status = true;
        this.endGame = false;
        this.inventory = new Inventory(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    
    public List<Entity> getEntityList() {
    	return entities;
    }
    
    public Inventory getInventory() {
    	return inventory;
    }
    
    public boolean getStatus() {
    	return status;
    }
    
    public void collectSword(Sword s) {
    	inventory.collectSword(s);
    }
    
    public void collectTreasure(Treasure t) {
    	inventory.collectTreasure(t);
    }
    
    public void collectKey(Key k) {
    	inventory.collectKey(k);
    }
    
    public void killPlayer() {
    	this.status = false;
    }
    
    public void endGame() {
    	this.endGame = true;
    }
    
    public boolean isGameOver() {
    	return endGame;
    }

    public List<Entity> obtainBlocked(int x, int y) {
        // TODO Auto-generated method stub
        
        return null;
    }
    

}
