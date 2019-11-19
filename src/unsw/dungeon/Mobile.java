/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Implements the Mobile abstract class, used with player and enemy entities
 */
public abstract class Mobile extends Entity {

    private BooleanProperty active;

    /**
     * @param x
     * @param y
     * @param dungeon
     */
    public Mobile(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.active = new SimpleBooleanProperty(true);
    }

    /**
     * 
     */
    public void inactive() {
    	if (getDungeon().isGameOver()) active.set(false);
    }
    
    /**
     * @return
     */
    public boolean isActive() {
    	return active.get();
    }
    
    /**
     * @return
     */
    public void setActive(boolean status) {
    	active.set(status);
    }
    
    /**
     * @return
     */
    public BooleanProperty getActiveProperty() {
    	return this.active;
    }
    
    /**
     * marks the player as dead and removes them from the dungeon
     */
    public void dead() {
        this.triggerSeeable(false);
        getDungeon().removeEntity(this);
        setActive(false);
    }
    
    /**
     * determines the interaction between blocking entities
     * 
     * @param dungeon
     * @param inventory
     * @param entity
     */
    public boolean blockingEntityBehaviour(Dungeon dungeon, Inventory inventory, Entity entity) {
    	Player player = dungeon.getPlayer();
    	if (entity instanceof Enemy) {
    		System.out.println("here");
			Enemy enemy = (Enemy) entity;
    		List<Sword> swordList = inventory.getSwordList();
    		if (enemy.isActive()) {
        		if (player.getInvincibleStatus()) {
        			entity.triggerSeeable(false);
        			dungeon.removeEntity(entity);
        			enemy.setActive(false);
        			System.out.println(enemy.isActive());
        			return true;
        		} else if (swordList.size() > 0) {
        			Sword sword = swordList.get(0);
        			inventory.useSword(sword);
        			entity.triggerSeeable(false);
        			dungeon.removeEntity(entity);
        			enemy.setActive(false);
        			System.out.println(enemy.isActive());
        			return true;
        		} else {
        			dungeon.endGame();
        			System.out.println("You Died!");
        			player.dead();
        			return true;
        		}
    		}
    	} else if (entity instanceof Door) {
			Door door = (Door) entity;
			List<Key> keys = inventory.getKeyList();
			for (Key k : keys) {
				if (inventory.useKey(door, k)) {
					entity.triggerSeeable(false);
					dungeon.removeEntity(entity);
					System.out.println("Door unlocked");
					keys.remove(k);
					return true;
				}
			}
    	}
    	return false;
	}
}