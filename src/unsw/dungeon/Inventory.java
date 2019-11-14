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
    	dungeon.removeEntity(treasure);
    	treasures.add(treasure);
    }
    
    public int getNumTreasures() {
    	return treasures.size();
    }
    
    public void collectSword(Sword sword) {
    	dungeon.removeEntity(sword);
    	swords.add(sword);
    }
    
    public boolean useSword() {
    	return true;
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
    			// return sword durability count
    		}
    	}
    	return 0;
    }
    
    public void collectKey(Key key) {
    	dungeon.removeEntity(key);
    	keys.add(key);
    }
    
    public void useKey(Door door, Key key) {
    	door.openDoor(key);
    }
    
    public List<Key> getKeyList() {
    	return keys;
    }
}