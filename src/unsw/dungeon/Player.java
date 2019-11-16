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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;


/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Mobile implements Observable {
	
    private Inventory inventory;
    private ArrayList<Observer> observers;
    private BooleanProperty active;
    private Boolean invincible;
    
    /**
     * 
     * Function create the player_object in square (x,y)
     * 
     * @param dungeon
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.inventory = new Inventory(getDungeon());
        this.observers = new ArrayList<>();
        this.active = new SimpleBooleanProperty(true);
        this.invincible = false;
    }
    
    public void dead() {
    	this.triggerSeeable(false);
    	getDungeon().removeEntity(this);
        active.set(false);
    }
    
    public BooleanProperty getActiveProperty() {
    	return this.active;
    }
  
    public Inventory getInventory() {
        return inventory;
    }
    
    public void setInvincibleStatus(boolean status) {
    	this.invincible = status;
    }
    
    public boolean getInvincibleStatus() {
    	return invincible;
    }
    
    public void blockingEntityBehaviour(Dungeon dungeon, Inventory inventory, Entity entity) {
    	if (entity instanceof Enemy) {
    		List<Sword> swordList = inventory.getSwordList();
    		if (getInvincibleStatus()) {
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
    	}
	}
    
    public void tokenEntityBehaviour(Dungeon dungeon, Inventory inventory, Entity entity) {
		if (entity instanceof Exit) {
			dungeon.endGame();
			System.out.println("Game Over");
		} else if (entity instanceof Treasure) {
			inventory.collectTreasure((Treasure) entity);
			entity.triggerSeeable(false);
			System.out.println("collecting treasure");
		} else if (entity instanceof Key) {
			inventory.collectKey((Key) entity);
			entity.triggerSeeable(false);
			System.out.println("collecting key");
		} else if (entity instanceof Sword) {
			inventory.collectSword((Sword) entity);
			entity.triggerSeeable(false);
			System.out.println("collecting sword");
		} else if (entity instanceof InvincibilityPotion) {
			InvincibilityPotion potion = (InvincibilityPotion) entity;
			potion.collectObject(inventory);
			entity.triggerSeeable(false);
			System.out.println("collecting potion");
			setInvincibleStatus(true);
			System.out.println("potion consumed");
		} else if (entity instanceof Portal) {
			Portal portal = (Portal) entity;
			dungeon.teleport(portal);
		}
    }
    
    public void changePlayerPosition(int x, int y) {
    	x().set(x);
    	y().set(y);
    }
    
    @Override
    public void move(Direction direction) {
    	List<Entity> entities = getDungeon().getEntityList();
		Dungeon dungeon = getDungeon();
		Inventory inventory = getInventory();
		boolean flag = false;
    	if (direction == Direction.UP) {
    		if (getY() > 0) {
        		for (Entity e : entities) {
        			if (e != null) {
        				if (e.getX() == getX() && e.getY() == getY() - 2 && !(e instanceof Switch)) flag = true; 
            			if (e.getX() == getX() && e.getY() == getY() - 1) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						if (!flag) {
            							e.y().set(e.getY() - 1);
            							y().set(getY() - 1);
            						}
            					}
                				return;
            				} else {
            					if (e instanceof Switch) {
            						Switch s = (Switch) e;
            						if (s.isActivated()) {
            							return;
            						} else {
            							continue;
            						}
            					}
            					y().set(getY() - 1);
            					tokenEntityBehaviour(dungeon, inventory, e);
            					return;
            				}
            			}
        			}
        		}
    			y().set(getY() - 1);
    		}
    	} else if (direction == Direction.DOWN) {
    		if (getY() < getDungeon().getHeight() - 1) {
        		for (Entity e : entities) {
        			if (e != null) {
        				if (e.getX() == getX() && e.getY() == getY() + 2 && !(e instanceof Switch)) flag = true; 
            			if (e.getX() == getX() && e.getY() == getY() + 1) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						if (!flag) {
            							e.y().set(e.getY() + 1);
            							y().set(getY() + 1);
            						}
            					}
                				return;
            				} else {
            					if (e instanceof Switch) {
            						Switch s = (Switch) e;
            						if (s.isActivated()) {
            							for (Entity boulder : entities) {
            								if (boulder instanceof Boulder) {
            									if (boulder.getX() == e.getX() && boulder.getY() == e.getY()) {
                        							boulder.y().set(boulder.getY() + 1);
                        							y().set(getY() + 1);
            									}
            								}
            							}
            							return;
            						} else {
            							continue;
            						}
            					}
            					y().set(getY() + 1);
            					tokenEntityBehaviour(dungeon, inventory, e);
            					return;
            				}
            			}
        			}
        		}
        		y().set(getY() + 1);
    		}
    	} else if (direction == Direction.RIGHT) {
    		if (getX() < getDungeon().getWidth() - 1) {
        		for (Entity e : entities) {
        			if (e != null) {
        				if (e.getX() == getX() + 2 && e.getY() == getY() && !(e instanceof Switch)) flag = true; 
            			if (e.getX() == getX() + 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						if (!flag) {
            							e.x().set(e.getX() + 1);
            							x().set(getX() + 1);
            						}
            					} else {
            						blockingEntityBehaviour(dungeon, inventory, e);
            					}
                				return;
            				} else {
            					if (e instanceof Switch) {
            						Switch s = (Switch) e;
            						if (s.isActivated()) {
            							return;
            						} else {
            							continue;
            						}
            					}
            					x().set(getX() + 1);
            					tokenEntityBehaviour(dungeon, inventory, e);
            					return;
            				}
            			}		
        			}
        		}
        		x().set(getX() + 1);
    		}
    	} else {
        	if (getX() > 0) {
        		for (Entity e : entities) {
        			if (e != null) {
        				if (e.getX() == getX() - 2 && e.getY() == getY() && !(e instanceof Switch)) flag = true;
            			if (e.getX() == getX() - 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						if (!flag) {
            							e.x().set(e.getX() - 1);
            							x().set(getX() - 1);
            						}
            					} else {
            						blockingEntityBehaviour(dungeon, inventory, e);
            					}
                				return;
            				} else {
            					if (e instanceof Switch) {
            						Switch s = (Switch) e;
            						if (s.isActivated()) {
            							return;
            						} else {
            							continue;
            						}
            					}
            					x().set(getX() - 1);
            					tokenEntityBehaviour(dungeon, inventory, e);
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

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update(this);
        }
    }
}