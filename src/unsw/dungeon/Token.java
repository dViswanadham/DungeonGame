/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Token extends Entity {
	
	private Inventory inventory;
	
	public Token(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
	}
	
	public boolean createInventory() {
		inventory = new Inventory(getDungeon());
		return true;
	}

	public boolean depositInventory() {
        return false;
		
	}

    public boolean collectObject(Inventory inventory) {
        getDungeon().removeEntity(this);
    	notifyObservers();
    	return true;
    }
}
