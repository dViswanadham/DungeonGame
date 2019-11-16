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
    
    @Override
    public void move(Direction direction) {
    	List<Entity> entities = getDungeon().getEntityList();
		Dungeon dungeon = getDungeon();
		Inventory inventory = getInventory();
    	if (direction == Direction.UP) {
    		if (getY() > 0) {
        		for (Entity e : entities) {
        			if (e != null) {
            			if (e.getX() == getX() && e.getY() == getY() - 1) {
            				if (e.isBlocking()) {
            					if (e instanceof Enemy) {
            						dungeon.endGame();
            						System.out.println("You Died!");
            					}
                				return;
            				} else {
            					y().set(getY() - 1);
            					if (e instanceof Exit) {
            						dungeon.endGame();
            						System.out.println("Game Over");
            					} else if (e instanceof Treasure) {
            						inventory.collectTreasure((Treasure) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting treasure");
            					} else if (e instanceof Key) {
            						inventory.collectKey((Key) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting key");
            					} else if (e instanceof Sword) {
            						inventory.collectSword((Sword) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting sword");
            					} else if (e instanceof InvincibilityPotion) {
            						InvincibilityPotion potion = (InvincibilityPotion) e;
            						potion.collectObject(inventory);
            						e.triggerSeeable(false);
            						System.out.println("collecting potion");
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
            			if (e.getX() == getX() && e.getY() == getY() + 1) {
            				if (e.isBlocking()) {
            					if (e instanceof Enemy) {
            						dungeon.endGame();
            						System.out.println("You Died!");
            					}
                				return;
            				} else {
            					y().set(getY() + 1);
            					if (e instanceof Exit) {
            						dungeon.endGame();
            						System.out.println("Game Over");
            					} else if (e instanceof Treasure) {
            						inventory.collectTreasure((Treasure) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting treasure");
            					} else if (e instanceof Key) {
            						inventory.collectKey((Key) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting key");
            					} else if (e instanceof Sword) {
            						inventory.collectSword((Sword) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting sword");
            					} else if (e instanceof InvincibilityPotion) {
            						InvincibilityPotion potion = (InvincibilityPotion) e;
            						potion.collectObject(inventory);
            						e.triggerSeeable(false);
            						System.out.println("collecting potion");
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
            			if (e.getX() == getX() + 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            					if (e instanceof Enemy) {
            						dungeon.endGame();
            						System.out.println("You Died!");
            					}
                				return;
            				} else {
            					x().set(getX() + 1);
            					if (e instanceof Exit) {
            						dungeon.endGame();
            						System.out.println("Game Over");
            					} else if (e instanceof Treasure) {
            						inventory.collectTreasure((Treasure) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting treasure");
            					} else if (e instanceof Key) {
            						inventory.collectKey((Key) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting key");
            					} else if (e instanceof Sword) {
            						inventory.collectSword((Sword) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting sword");
            					} else if (e instanceof InvincibilityPotion) {
            						InvincibilityPotion potion = (InvincibilityPotion) e;
            						potion.collectObject(inventory);
            						e.triggerSeeable(false);
            						System.out.println("collecting potion");
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
            			if (e.getX() == getX() - 1 && e.getY() == getY()) {
            				if (e.isBlocking()) {
            					if (e instanceof Enemy) {
            						dungeon.endGame();
            						System.out.println("You Died!");
            					}
                				return;
            				} else {
            					x().set(getX() - 1);
            					if (e instanceof Exit) {
            						dungeon.endGame();
            						System.out.println("Game Over");
            					} else if (e instanceof Treasure) {
            						inventory.collectTreasure((Treasure) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting treasure");
            					} else if (e instanceof Key) {
            						inventory.collectKey((Key) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting key");
            					} else if (e instanceof Sword) {
            						inventory.collectSword((Sword) e);
            						e.triggerSeeable(false);
            						System.out.println("collecting sword");
            					} else if (e instanceof InvincibilityPotion) {
            						InvincibilityPotion potion = (InvincibilityPotion) e;
            						potion.collectObject(inventory);
            						e.triggerSeeable(false);
            						System.out.println("collecting potion");
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
            o.update(this);
        }
    }
}