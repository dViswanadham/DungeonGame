/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class InvincibilityPotion extends Entity {

	private int duration;
	
    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.duration = 5000;
        
    }

    public int getDuration() {
    	return this.duration;
    }
    
    public void onPlayerGetPotion() {
    	// update player and monster interaction
    	System.out.println("updating player and monster interaction");
    }
    
    public void onPotionExpires() {
    	// return state of monster and player interaction
    }
    
    @Override
    public boolean collectObject(Inventory inventory) {
    	getDungeon().delEntity(this);
    	onPlayerGetPotion();
    	notifyObservers();
    	return true;
    }
}
