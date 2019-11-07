/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Token extends Entity {
	
	private Inventory inventory;
	
	public Token(int x, int y) {
        super(x,y);
	}
	
	public boolean createInventory() {
		inventory = new Inventory();
		return true;
	}

	public boolean depositInventory() {
	    
        return false;
		
	}

    public boolean collectObject(Dungeon dungeon, Inventory inventory) {
        // inventory.addItem(this);
        dungeon.removeEntity(this);
    	notifyObservers();
    	return true;
    }
}
