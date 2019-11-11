/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.List;

/**
 * Implements the Mobile abstract class, used with entities in the dungeon that are moveable
 */
public abstract class Mobile extends Entity {

    private Dungeon dungeon;
    private boolean active;

    public Mobile(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.dungeon = dungeon;
        this.active = true;
    }

    public Dungeon dungeon() {
        return dungeon;
    }

    public void inactive() {
    	if (dungeon.isGameOver()) this.active = false;
    }
    
    public boolean isActive() {
    	return active;
    }
    
    public void move(Direction direction) {
    	
    	if (!isActive()) {
    		List<Entity> entities = dungeon.getEntityList();
        	if (direction == Direction.UP) {
            	for (Entity e : entities) {
            		if (e.getX() == this.getX() && e.getY() == (this.getY() + 1)) {
            			if (e.isBlocking()) return;
            			else y().set(this.getY() + 1);
            		}
            	} 	
        	} else if (direction == Direction.DOWN) {
            	for (Entity e : entities) {
            		if (e.getX() == this.getX() && e.getY() == (this.getY() - 1)) {
            			if (e.isBlocking()) return;
            			else y().set(this.getY() - 1);
            		}
            	} 	
        	} else if (direction == Direction.RIGHT) {
            	for (Entity e : entities) {
            		if (e.getX() == (this.getX() + 1) && e.getY() == this.getY()) {
            			if (e.isBlocking()) return;
            			else x().set(this.getX() + 1);
            		}
            	} 	
        	} else {
            	for (Entity e : entities) {
            		if (e.getX() == (this.getX() - 1) && e.getY() == this.getY()) {
            			if (e.isBlocking()) return;
            			else x().set(this.getX() - 1);
            		}
            	} 	
        	}	
        	notifyObservers();
    	}
    }
}