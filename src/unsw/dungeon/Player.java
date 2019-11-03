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
public class Player extends Mobile implements ObsInterface {
    private Chest chest;
    private ArrayList<Constructor> observePl;
    private BooleanProperty active;
    private SimpleIntegerProperty health;
    private int totalHealth;
    
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
        this.chest = new Chest(dungeon);
        this.observePl = new ArrayList<>();
        this.active = new SimpleBooleanProperty(true);
        this.health = new SimpleIntegerProperty(5);
        this.totalHealth = 5;
    }
    
    public void dead() {
    	this.setVisibility(false);
    	dungeon().removeEntity(this);
        setAlive(false);
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
        if (!active()) {
            
            return;
        }
        
        if (getY() <= 0) {
            
            return;
        }
        
        if (blocked(getX(), getY() - 1)) {
            
            y().set(getY() - 1);
        }
        
        alertObs();
    }
    
    /**
     * Moves the player_object down one square, scanning for a free space
     * and alerting all observers
     */
    public void moveDown() {
        if (!active()) {
            
            return;
        }
        
        if (getY() >= dungeon().getHeight() - 1) {
            
            return;
        }
        
        if (blocked(getX(), getY() + 1)) {
            
            y().set(getY() + 1);
        }
        
        alertObs();
    }
    
    /**
     * Moves the player_object left one square, scanning for a free space
     * and alerting all observers
     */
    public void moveLeft() {
        if (!active()) {
            
            return;
        }
        
        if (getX() <= 0) {
            
            return;
        }
        
        if (blocked(getX() - 1, getY())) {
            
            x().set(getX() - 1);
        }
        
        alertObs();
    }
    
    /**
     * Moves the player_object right one square, scanning for a free space
     * and alerting all observers
     */
    public void moveRight() {
        if (!active()) {
            
            return;
        }
        
        if (getX() >= dungeon().getWidth() - 1) {
            
            return;
        }
        
        if (blocked(getX() + 1, getY())) {
            
            x().set(getX() + 1);
        }
        
        alertObs();
    }
    
    public SimpleIntegerProperty obtainHealth() {
        
    	return this.health;
    }
    
    public void injured() {
        
    	this.health.set(this.health.get() - 1);
    }
    
    public int obtainTotalHealth() {
        
    	return this.totalHealth;
    }
    
    public void fixHealthPool(int health) {
        
    	this.totalHealth = health;
    	this.health.set(health);
    }
    
    /**
     * 
     * @param x (x co-ord of blocking entity)
     * @param y (y co-ord of blocking entity)
     * @return boolean (i.e. negative if the player_object cannot move to the desired location)
     */
    public boolean blocked(int x, int y) {
        List<Entity> blocking = dungeon().getCollidingEntity(x, y);
        
        if (blocking == null) {
            
            return true;
        }
        
        boolean blocked = true;
        
        for(Entity f : blocking) {
            if (!f.scanSquare(this)) {
                
                return false;
            }
        }
        
        return true;
    }

    public Chest obtainChest() {
        
        return chest;
    }
    
    /**
     * 
     * Function triggers Invincibility based on correct token (Potion)
     * 
     * @param f
     * @return boolean (i.e. positive if the player_object manages to kill an enemy and negative if the player_object dies instead)
     */
    public boolean triggerInvincibility(Foe f) {
        if (chest.useConsumable(f)) {
            
            return true;
        }
        
        return false;
    }
    
    /**
     * 
     * Function determines if the player_object is blocked by a door or not
     * 
     * @param door
     * @return boolean
     */
    public boolean scanSquare(Door door) {
        
        return chest.useConsumable(door);
    }
    
    /**
     * 
     * Function determines whether player_object is blocked by a barrier such as a wall
     * 
     * @param f
     * @return boolean
     */
    public boolean scanSquare(Barrier f) {
        
        return false;
    }
    
    /**
     * 
     * Function is in charge of mobile entity interactions e.g. with foes
     * 
     */
    public boolean scanSquare(Mobile f) {
        if (f instanceof Foe) {
            
            f.scanSquare(this);
        }
        
        return true;
    }

    @Override
    public void enlistObs(Constructor c) {
        
        observePl.add(c);
    }

    @Override
    public void discardObs(Constructor c) {
        if (observePl.contains(c)) {
            
            observePl.remove(c);
        }
    }

    @Override
    public void alertObs() {
        for(Constructor c : observePl) {
            
            c.update(this);
        }
    }
}