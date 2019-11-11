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
     * 
     * @param x
     * @param y
     */
    public Entity(int x, int y, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeon = dungeon;
        this.seeable = new SimpleBooleanProperty(true);
        this.observers = new ArrayList<>();
    }
    
    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }
    
    public Dungeon getDungeon() {
    	return dungeon;
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
    
    public boolean collectObject(Dungeon dungeon, Inventory inventory) {
    	// inventory.addItem(this);
    	dungeon.removeEntity(this);
    	notifyObservers();
    	return true;
    }
    
	public boolean isBlocking() {
		return false;
	}
    
	@Override
	public void registerObserver(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
}