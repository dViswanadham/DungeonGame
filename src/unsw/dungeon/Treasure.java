/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Treasure extends Token {

	
    public Treasure(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }
    
    @Override
    public boolean collectObject(Inventory inventory) {
    	inventory.collectTreasure(this);
        getDungeon().delEntity(this);
    	return true;
    }
}
