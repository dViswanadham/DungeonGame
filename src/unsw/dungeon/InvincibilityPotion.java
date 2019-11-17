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
	private Player player;
	
    public InvincibilityPotion(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.duration = 25;
        this.player = getDungeon().getPlayer();
        
    }

    public int getDuration() {
    	return this.duration;
    }
    
    public void onPlayerGetPotion() {
    	player.setInvincibleStatus(true);
    	System.out.println("potion consumed");
    }
    
    public void onPotionExpires() {
    	player.setInvincibleStatus(false);
    	System.out.println("potion expired");
    }
    
    @Override
    public boolean collectObject(Inventory inventory) {
    	getDungeon().removeEntity(this);
    	onPlayerGetPotion();
    	notifyObservers();
    	return true;
    }
}
