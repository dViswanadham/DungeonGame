/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores tokens that the player_object saves
 */
public class Inventory {
    private List<Treasure> treasures;
    private List<Sword> swords;
    private List<Key> keys;
    private Dungeon dungeon;

    /**
     * @param dungeon
     */
    public Inventory(Dungeon dungeon) {
    	this.dungeon = dungeon;
        this.treasures = new ArrayList<>();
        this.swords = new ArrayList<>();
        this.keys = new ArrayList<>();
    }
    
    /**
     * @param treasure
     */
    public void collectTreasure(Treasure treasure) {
    	System.out.println("removing treasure");
    	dungeon.removeEntity(treasure);
    	treasures.add(treasure);
    }
    
    /**
     * @return
     */
    public int getNumTreasures() {
    	return treasures.size();
    }
    
    /**
     * @param sword
     */
    public void collectSword(Sword sword) {
    	System.out.println("removing sword");
    	dungeon.removeEntity(sword);
    	swords.add(sword);
    }
    
    /**
     * @param sword
     */
    public void useSword(Sword sword) {
    	sword.setDurability(sword.getDurability() - 1);
    	if (sword.getDurability() == 0) {
    		breakSword(sword);
    		System.out.println("sword broke");
    	}
    	System.out.println("sword used");
    }
    
    /**
     * @return
     */
    public List<Sword> getSwordList() {
    	return swords;
    }
    
    /**
     * @param sword
     */
    public void breakSword(Sword sword) {
    	swords.remove(sword);
    }
    
    /**
     * @param sword
     * @return
     */
    public int getSwordUsability(Sword sword) {
    	for (Sword s: swords) {
    		if (s.equals(sword)) {
    			return sword.getDurability();
    		}
    	}
    	return 0;
    }
    
    /**
     * @param key
     */
    public void collectKey(Key key) {
    	System.out.println("removing key");
    	dungeon.removeEntity(key);
    	keys.add(key);
    }
    
    /**
     * @param door
     * @param key
     * @return
     */
    public boolean useKey(Door door, Key key) {
    	return door.openDoor(key);
    }
    
    /**
     * @return
     */
    public List<Key> getKeyList() {
    	return keys;
    }
}