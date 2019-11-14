/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Sword extends Token {
	
	private int durability;
	
    public Sword(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.durability = 5;
    }
    
    public int getDurability() {
    	return durability;
    }
    
    public void setDurability(int newDurability) {
    	this.durability = newDurability;
    }

    @Override
    public boolean collectObject(Inventory inventory) {
    	inventory.collectSword(this);
    	getDungeon().removeEntity(this);
    	notifyObservers();
    	return true;
    }
}
