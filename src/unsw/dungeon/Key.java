/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Key extends Token {

	private int id;
	
    public Key(int x, int y, Dungeon dungeon, Inventory inventory, int id) {
        super(x, y, dungeon);
        this.id = id;
    }

    @Override
    public boolean collectObject(Inventory inventory) {
    	inventory.collectKey(this);
    	getDungeon().addEntity(this);
    	notifyObservers();
    	return true;
    }
    
    public void applyKey(Door door, Inventory inventory) {
    	inventory.useKey(door, this);
    }
}
