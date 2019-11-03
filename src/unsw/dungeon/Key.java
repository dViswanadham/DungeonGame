/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 24/10/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Key extends Token {

	private int id;
	
    public Key(int x, int y, int id) {
        super(x,y);
        this.id = id;
    }

    @Override
    public boolean collectObject(Dungeon dungeon, Inventory inventory) {
    	inventory.addObject(this);
    	Dungeon.removeObject(this);
    	notifyObservers();
    	return true;
    }
    
    public boolean applyKey(Door door, Inventory inventory) {
    	if (door.getDoorID() == this.id) {
    		abandonKey(door, inventory);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean abandonKey(Door door, Inventory inventory) {
    	int id = door.getDoorID();
    	for (Token t : inventory.getTokenList()) {
    		// remove key based on matching id with door
    		return true;
    	}
    }
}
