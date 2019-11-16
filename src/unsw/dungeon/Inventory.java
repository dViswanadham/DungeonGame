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

    public Inventory(Dungeon dungeon) {
    	this.dungeon = dungeon;
        this.treasures = new ArrayList<>();
        this.swords = new ArrayList<>();
        this.keys = new ArrayList<>();
    }
    
    public void collectTreasure(Treasure treasure) {
    	System.out.println("removing treasure");
    	dungeon.delEntity(treasure);
    	treasures.add(treasure);
    }
    
    public int getNumTreasures() {
    	return treasures.size();
    }
    
    public void collectSword(Sword sword) {
    	System.out.println("removing sword");
    	dungeon.delEntity(sword);
    	swords.add(sword);
    }
    
    public void useSword(Sword sword) {
    	sword.setDurability(sword.getDurability() - 1);
    	if (sword.getDurability() == 0) {
    		breakSword(sword);
    	}
    }
    
    public List<Sword> getSwordList() {
    	return swords;
    }
    
    public void breakSword(Sword sword) {
    	swords.remove(sword);
    }
    
    public int getSwordUsability(Sword sword) {
    	for (Sword s: swords) {
    		if (s.equals(sword)) {
    			return sword.getDurability();
    		}
    	}
    	return 0;
    }
    
    public void collectKey(Key key) {
    	System.out.println("removing key");
    	dungeon.delEntity(key);
    	keys.add(key);
    }
    
    public void useKey(Door door, Key key) {
    	door.openDoor(key);
    }
    
    public List<Key> getKeyList() {
    	return keys;
    }
}