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
    
    @Override
    public boolean isBlocking() {
    	return true;
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
    
    public boolean tokenEntityBehaviour(Dungeon dungeon, Inventory inventory, Entity entity) {
		if (entity instanceof Exit) {
			dungeon.endGame();
			System.out.println("Game Over");
			return true;
		} else if (entity instanceof Treasure) {
			inventory.collectTreasure((Treasure) entity);
			entity.triggerSeeable(false);
			System.out.println("collecting treasure");
			return true;
		} else if (entity instanceof Key) {
			inventory.collectKey((Key) entity);
			entity.triggerSeeable(false);
			System.out.println("collecting key");
			return true;
		} else if (entity instanceof Sword) {
			inventory.collectSword((Sword) entity);
			entity.triggerSeeable(false);
			System.out.println("collecting sword");
			return true;
		} else if (entity instanceof InvincibilityPotion) {
			InvincibilityPotion potion = (InvincibilityPotion) entity;
			potion.collectObject(inventory);
			entity.triggerSeeable(false);
			System.out.println("collecting potion");
			setInvincibleStatus(true);
			System.out.println("potion consumed");
			return true;
		} else if (entity instanceof Portal) {
			Portal portal = (Portal) entity;
			dungeon.teleport(portal);
		}
		return false;
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
		boolean boulderFlag = false;
    	if (direction == Direction.UP) {
    		if (getY() > 0) {
        		for (Entity e : entities) {
        			if (e != null) {
        				Boulder boulder = null;
        				for (Entity entity : entities) {
            				if (entity.getX() == getX() && entity.getY() == getY() - 2 && !(entity instanceof Switch)) flag = true;
            				if (entity.getX() == getX() && entity.getY() == getY() - 1 && entity instanceof Boulder) {
            					boulder = (Boulder) entity;
            					boulderFlag = true; 
            				}
        				}
            			if (e.getX() == getX() && e.getY() == getY() - 1) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						boulder = (Boulder) e;
            						if (!flag) {
               							e.y().set(e.getY() - 1);
            							y().set(getY() - 1);
            							boulder.isActivated();
            						}
            					} else {
            						blockingEntityBehaviour(dungeon, inventory, e);
            					}
            					return;
            				} else {
            					if (!boulderFlag || e instanceof Switch) {
                					if (boulder != null && e instanceof Switch && !flag) {
                						boulder.y().set(boulder.getY() - 1);
                						y().set(getY() - 1);
                					} else if (!boulderFlag && e instanceof Switch) {
                						y().set(getY() - 1);
                					} else {
                						if (tokenEntityBehaviour(dungeon, inventory, e)) {
                							y().set(getY() - 1);
                						}
                					}
            					}
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
        				Boulder boulder = null;
        				for (Entity entity : entities) {
            				if (entity.getX() == getX() && entity.getY() == getY() + 2 && !(entity instanceof Switch)) flag = true;
            				if (entity.getX() == getX() && entity.getY() == getY() + 1 && entity instanceof Boulder) {
            					boulder = (Boulder) entity;
            					boulderFlag = true; 
            				}
        				}
            			if (e.getX() == getX() && e.getY() == getY() + 1) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						boulder = (Boulder) e;
            						if (!flag) {
            							e.y().set(e.getY() + 1);
            							y().set(getY() + 1);
            							boulder.isActivated();
            						}  
            					} else {
            						blockingEntityBehaviour(dungeon, inventory, e);
            					}
                				return;
            				} else {
            					if (!boulderFlag || e instanceof Switch) {
                					if (boulder != null && e instanceof Switch && !flag) {
                						boulder.y().set(boulder.getY() + 1);
                						y().set(getY() + 1);
                					} else if (!boulderFlag && e instanceof Switch) {
                						y().set(getY() + 1);
                					} else {
                						if (tokenEntityBehaviour(dungeon, inventory, e)) {
                							y().set(getY() + 1);
                						}
                					}
            					}
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
        				Boulder boulder = null;
        				for (Entity entity : entities) {
            				if (entity.getX() == getX() + 2 && entity.getY() == getY() && !(entity instanceof Switch)) flag = true;
            				if (entity.getX() == getX() + 1 && entity.getY() == getY() && entity instanceof Boulder) {
            					boulder = (Boulder) entity;
            					boulderFlag = true; 
            				}
        				}
            			if (e.getX() == getX() + 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						boulder = (Boulder) e;
            						if (!flag) {
               							e.x().set(e.getX() + 1);
               							x().set(getX() + 1);
            							boulder.isActivated();
            						}
            					} else {
            						blockingEntityBehaviour(dungeon, inventory, e);
            					}
            					return;
            				} else {
            					if (!boulderFlag || e instanceof Switch) {
                  					if (boulder != null && e instanceof Switch && !flag) {
                  						boulder.x().set(boulder.getX() + 1);
                  						x().set(getX() + 1);
                  					} else if (!boulderFlag && e instanceof Switch) {
                  						x().set(getX() + 1);
                  					} else {
                  						if (tokenEntityBehaviour(dungeon, inventory, e)) {
                  							x().set(getX() + 1);
                  						}
                  					}
            					}
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
        				Boulder boulder = null;
        				for (Entity entity : entities) {
            				if (entity.getX() == getX() - 2 && entity.getY() == getY() && !(entity instanceof Switch)) flag = true;
            				if (entity.getX() == getX() - 1 && entity.getY() == getY() && entity instanceof Boulder) {
            					boulder = (Boulder) entity;
            					boulderFlag = true; 
            				}
        				}
            			if (e.getX() == getX() - 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            					if (e instanceof Boulder) {
            						boulder = (Boulder) e;
            						if (!flag) {
               							e.x().set(e.getX() - 1);
            							x().set(getX() - 1);
            							boulder.isActivated();
            						}
            					} else {
            						blockingEntityBehaviour(dungeon, inventory, e);
            					}
            					return;
            				} else {
            					if (!boulderFlag || e instanceof Switch) {
                					if (boulder != null && e instanceof Switch && !flag) {
                						boulder.x().set(boulder.getX() - 1);
                						x().set(getX() - 1);
                					} else if (!boulderFlag && e instanceof Switch) {
                						x().set(getX() - 1);
                					} else {
                  						if (tokenEntityBehaviour(dungeon, inventory, e)) {
                  							x().set(getX() - 1);
                  						}
                					}
            					}
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
        	o.refresh(this);
        }
    }
}