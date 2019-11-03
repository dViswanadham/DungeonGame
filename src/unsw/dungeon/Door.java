/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 24/10/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Door extends Entity {

	private int id;
	private Key key;
	private boolean state;
	public static final boolean OPEN = true;
	public static final boolean CLOSED = false;
	
    public Door(int x, int y, int id) {
    	super(x, y);
    	this.id = id;
    	this.state = CLOSED;
    }

    public int getDoorID() {
    	return this.id;
    }
    
    public void setKey(Key key) {
    	this.key = key;
    }
    
    public Key getKey() {
    	return this.key;
    }
    
    public boolean openDoor(Key key) {
    	if (this.key.equals(key)) {
    		changeState(OPEN);
    		return true;
    	}
    	return false;
    }
    
    public void changeState(boolean newState) {
    	this.state = newState;
    }
    
    @Override
    public boolean isBlocking() {
    	return this.state == Door.CLOSED;
    }
}
