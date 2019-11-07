/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;


/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Mobile implements Observable {
    private Inventory inventory;
    private ArrayList<Observer> observers;
    private BooleanProperty active;
    private Dungeon dungeon;
    private int x, y;
    
    /**
     * 
     * Function create the player_object in square (x,y)
     * 
     * @param dungeon
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.dungeon = dungeon;
        this.x = x;
        this.y = y;
        this.inventory = new Inventory();
        this.observers = new ArrayList<>();
        this.active = new SimpleBooleanProperty(true);
    }
    
    public void dead() {
    	this.triggerSeeable(false);
    	dungeon.removeEntity(this);
        createActive(false);
        active.set(false);
    }
    
    public BooleanProperty activeAttribute() {
    	return this.active;
    }
    
    /**
     * Moves the player_object up one square, scanning for a free space
     * and alerting all observers
     */
    public void moveUp() {
    	List<Entity> entities = dungeon.getEntityList();
    	if (!active()) {
    		return;
    	} else {
        	for (Entity e : entities) {
        		if (e.getX() == x && e.getY() == y++) {
        			if (e.isBlocking()) return;
        			else y().set(y++);
        		}
        	} 		
    	}
        notifyObservers();
    }
    
    /**
     * Moves the player_object down one square, scanning for a free space
     * and alerting all observers
     */
    public void moveDown() {
    	
    	List<Entity> entities = dungeon.getEntityList();
    	if (!active()) {
    		return;
    	} else {
        	for (Entity e : entities) {
        		if (e.getX() == x && e.getY() == y--) {
        			if (e.isBlocking()) return;
        			else y().set(y--);
        		}
        	} 		
    	}
        notifyObservers();
    }
    
    /**
     * Moves the player_object left one square, scanning for a free space
     * and alerting all observers
     */
    public void moveLeft() {
    	List<Entity> entities = dungeon.getEntityList();
    	if (!active()) {
    		return;
    	} else {
        	for (Entity e : entities) {
        		if (e.getX() == x-- && e.getY() == y) {
        			if (e.isBlocking()) return;
        			else x().set(x--);
        		}
        	} 		
    	}
        notifyObservers();
    }
    
    /**
     * Moves the player_object right one square, scanning for a free space
     * and alerting all observers
     */
    public void moveRight() {
    	List<Entity> entities = dungeon.getEntityList();
    	if (!active()) {
    		return;
    	} else {
        	for (Entity e : entities) {
        		if (e.getX() == x++ && e.getY() == y) {
        			if (e.isBlocking()) return;
        			else y().set(x++);
        		}
        	} 		
    	}
        notifyObservers();
    }
  
    public Inventory obtainInventory() {
        return inventory;
    }
    
    /**
     * 
     * Function triggers Invincibility based on correct token (Potion)
     * 
     * @param f
     * @return boolean (i.e. positive if the player_object manages to kill an enemy and negative if the player_object dies instead)
     */
//    public boolean triggerInvincibility(Foe f) {
//        if (inventory.applyToken(f)) {
//            
//            return true;
//        }
//        
//        return false;
//    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            
            o.update(this);
        }
    }
}