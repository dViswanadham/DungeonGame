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
	
	private int x, y;
	private Dungeon dungeon;
	private Inventory inventory;
	
	public Token(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.x = x;
        this.y = y;
        this.dungeon = dungeon;
	}
	
	public boolean createInventory() {
		inventory = new Inventory(dungeon);
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
