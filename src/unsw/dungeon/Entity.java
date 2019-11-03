/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 24/10/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity implements Observable {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private List<Observer> observers;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public boolean isBlocking() {
    	return false;
    }
    
    @Override
    public String toString() {
    	return String.format("<%s | %d, %d>", this.getClass().getName(), this.getX(), this.getY());
    }
    
    @Override
    public void registerObserver(Observer observer) {
    	observers.add(observer);
    }
    
    @Override
    public void notifyObservers() {
    	observers.forEach(observer -> observer.update(this));
    }

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
}
