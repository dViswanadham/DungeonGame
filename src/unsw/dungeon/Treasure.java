/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Treasure extends Token {

    public Treasure(int x, int y) {
        super(x, y);
    }
    
    @Override
    public boolean collectObject(Dungeon dungeon, Inventory inventory) {
        // inventory.addItem(this);
        dungeon.removeEntity(this);
    	return true;
    }
}
