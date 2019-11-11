/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Sword extends Token {

	private int x, y;
	private Dungeon dungeon;
	
    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.x = x;
        this.y = y;
        this.dungeon = dungeon;
    }

    @Override
    public boolean collectObject(Dungeon dungeon, Inventory inventory) {
    	// inventory.addItem(this);
    	dungeon.removeEntity(this);
    	notifyObservers();
    	return true;
    }
}
