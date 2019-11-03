/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 24/10/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Key extends Entity {

	private int id;
	
    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    public boolean obtainKey(Inventory inventory) {
    	inventory.addObject(this);
    	Dungeon.removeObject(this);
    	return true;
    }
    
    public boolean applyKey(Door door) {
    	if (door.getDoorID() == this.id) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
