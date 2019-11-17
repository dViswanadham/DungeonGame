/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
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
	
    /**
     * @param x
     * @param y
     * @param dungeon
     * @param id
     */
    public Door(int x, int y, Dungeon dungeon, int id) {
    	super(x, y, dungeon);
    	this.id = id;
    	this.state = CLOSED;
    }

    /**
     * @return
     */
    public int getDoorID() {
    	return this.id;
    }
    
    /**
     * @param key
     */
    public void setKey(Key key) {
    	this.key = key;
    }
    
    /**
     * @return
     */
    public Key getKey() {
    	return this.key;
    }
    
    /**
     * @param key
     * @return
     */
    public boolean openDoor(Key key) {
    	if (this.id == key.getID()) {
    		changeState(OPEN);
    		return true;
    	}
    	return false;
    }
    
    /**
     * @param newState
     */
    public void changeState(boolean newState) {
    	this.state = newState;
    }
    
    /**
     *
     */
    @Override
    public boolean isBlocking() {
    	return this.state == Door.CLOSED;
    }
}
