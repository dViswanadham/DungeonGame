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
public abstract class Entity implements Observable {
    // IntegerProperty is used so that changes to the entities position can be
    // externally observed. 
    private IntegerProperty x, y;
    private BooleanProperty seeable;
    private ArrayList<Observer> observers;
    private Dungeon dungeon;
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     * @param dungeon
     */
    public Entity(int x, int y, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = dungeon;
        this.seeable = new SimpleBooleanProperty(true);
        this.observers = new ArrayList<>();
    }
    
    /**
     * @return
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * @return
     */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * @return
     */
    public Dungeon getDungeon() {
    	return dungeon;
    }
    
    /**
     * @return
     */
    public BooleanProperty seeable() {
    	return seeable;
    }
    
    /**
     * @param transparency
     */
    public void triggerSeeable(boolean transparency) {
    	this.seeable.set(transparency);
    }
    
    /**
     * @return
     */
    public int getY() {
        return y().get();
    }
    
    /**
     * @return
     */
    public int getX() {
        return x().get();
    }
    
    /**
     * @param inventory
     * @return
     */
    public boolean collectObject(Inventory inventory) {
    	getDungeon().removeEntity(this);
    	notifyObservers();
    	return true;
    }
    
	/**
	 * @return
	 */
	public boolean isBlocking() {
		return false;
	}
    
	/**
	 *
	 */
	@Override
	public void registerObserver(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
	}

	/**
	 *
	 */
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/**
	 *
	 */
	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.refresh(this);
		}
	}
}