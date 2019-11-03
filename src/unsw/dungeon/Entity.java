package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {
    // IntegerProperty is used so that changes to the entities position can be
    // externally observed. 
    private IntegerProperty x, y;
    private BooleanProperty seeable;
    
    /**
     * Create an entity positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.seeable = new SimpleBooleanProperty(true);
    }

    public IntegerProperty x() {
        
        return x;
    }

    public IntegerProperty y() {
        
        return y;
    }
    
    public BooleanProperty seeable() {
        
    	return seeable;
    }
    
    public void triggerSeeable(boolean transparency) {
        
    	this.seeable.set(transparency);
    }

    public int getY() {
        
        return y().get();
    }

    public int getX() {
        
        return x().get();
    }
    
    /**
     * 
     * Function scans for any interaction against a token
     * 
     * @param object
     * @return boolean
     */
    public boolean scanSquare(Token object) {
        
        return false;
    }
    
    /**
     * 
     * Function scans for any interaction against a barrier
     * 
     * @param object
     * @return boolean
     */
    public boolean scanSquare(Wall object) {
        
        return false;
    }
    
    /**
     * 
     * Function scans for any interaction against a mobile entity (e.g. foes)
     * 
     * @param object
     * @return boolean
     */
    public boolean scanSquare(Mobile object) {
        
        return false;
    }
}