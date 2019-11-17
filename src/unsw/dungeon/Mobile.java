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
 * Implements the Mobile abstract class, used with entities in the dungeon that are moveable
 */
public abstract class Mobile extends Entity {

    private BooleanProperty active;

    public Mobile(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.active = new SimpleBooleanProperty(true);
    }

    public void inactive() {
    	if (getDungeon().isGameOver()) active.set(false);
    }
    
    public boolean isActive() {
    	return active.get();
    }
    
    public BooleanProperty getActiveProperty() {
    	return this.active;
    }
    
    public void dead() {
        this.triggerSeeable(false);
        getDungeon().removeEntity(this);
        inactive();
    }
    
    public void blockingEntityBehaviour(Dungeon dungeon, Inventory inventory, Entity entity) {
    	Player player = dungeon.getPlayer();
    	if (entity instanceof Enemy) {
    		List<Sword> swordList = inventory.getSwordList();
    		if (player.getInvincibleStatus()) {
    			entity.triggerSeeable(false);
    			dungeon.removeEntity(entity);
    		} else if (swordList.size() > 0) {
    			Sword sword = swordList.get(0);
    			inventory.useSword(sword);
    			entity.triggerSeeable(false);
    			dungeon.removeEntity(entity);
    		} else {
    			dungeon.endGame();
    			System.out.println("You Died!");
    			player.dead();
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
					break;
				}
			}
    	} else if (entity instanceof Player) {
			dungeon.endGame();
			System.out.println("You Died!");
			player.dead();
    	}
	}
    
    public void move(Direction direction) {
		List<Entity> entities = getDungeon().getEntityList();
    	if (direction == Direction.UP) {
    		if (getY() > 0) {
        		for(Entity e : entities) {
        			if (e != null) {
            			if (e.getX() == getX() && e.getY() == getY() - 1) {
            				if (e.isBlocking()) {
            				    
                				return;
                				
            				} else {
            					y().set(getY() - 1);
            					
            					return;
            				}
            			}
        			}
        		}
        		
    			y().set(getY() - 1);
    		}
    		
    	} else if (direction == Direction.DOWN) {
    		if (getY() < getDungeon().getHeight() - 1) {
        		for(Entity e : entities) {
        			if (e != null) {
            			if (e.getX() == getX() && e.getY() == getY() + 1) {
            				if (e.isBlocking()) {
            				    
                				return;
                				
            				} else {
            					y().set(getY() + 1);
            					
            					return;
            				}
            			}
        			}
        		}
        		
        		y().set(getY() + 1);
    		}
    		
    	} else if (direction == Direction.RIGHT) {
    		if (getX() < getDungeon().getWidth() - 1) {
        		for(Entity e : entities) {
        			if (e != null) {
            			if (e.getX() == getX() + 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            				    
                				return;
                				
            				} else {
            					x().set(getX() + 1);
            					
            					return;
            				}
            			}		
        			}
        		}
        		
        		x().set(getX() + 1);
    		}
    		
    	} else {
        	if (getX() > 0) {
        		for(Entity e : entities) {
        			if (e != null) {
            			if (e.getX() == getX() - 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            				    
                				return;
                				
            				} else {
            					x().set(getX() - 1);
            					
            					return;
            				}
            			}
        			}
        		}
        		
        		x().set(getX() - 1);
        	}
    	}
    	
    	notifyObservers();
    }
}