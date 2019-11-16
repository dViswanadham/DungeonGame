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
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private boolean status;
    private Inventory inventory;
    private boolean endGame;
    private Goals objectives;
    private FlagDungeon flagDungeon;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.status = true;
        this.endGame = false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void removeEntity(Entity entity) {
        if (entities.contains(entity)) {
            
            entities.remove(entity);
        }
    }    
    
    public void createAim(Goals objectives) {
        this.objectives = objectives;
    }
    
    public Goals obtainAim() {
        return this.objectives;
    }
    
    public List<Entity> obtainTargetSquare(int x, int y) {
        List<Entity> target = new ArrayList<>();
        
        for(Entity e : entities) {
            if (e.getX() == x && e.getY() == y) {
                
                target.add(e);
            }
        }
        
        return target;
    }
    
    public List<Entity> obtainAdjacentSquares(int x, int y) {
        List<Entity> adjacent = new ArrayList<>();
        
        for(Entity e : entities) {
            if (e == null) {
                
                continue;
            }
            
            if (e.getX() == (x + 1) && e.getY() == y) {
                
                adjacent.add(e);
            }
            
            if (e.getX() == (x - 1) && e.getY() == y) {
                
                adjacent.add(e);
            }
            
            if (e.getX() == x && e.getY() == (y + 1)) {
                
                adjacent.add(e);
            }
            
            if (e.getX() == x && e.getY() == (y - 1)) {
                
                adjacent.add(e);
            }
        }
        
        return adjacent;
    }
    
    public void createSignal() {
        
        this.flagDungeon.addTransmitter(this);
    }
    
    public ArrayList<FlagDungeonClient> obtainSignal() {
        ArrayList<FlagDungeonClient> receiver = new ArrayList<>();
        
        for(Entity entity : entities) {
            if (entity instanceof FlagDungeonClient) {
                
                receiver.add((FlagDungeonClient) entity);
            }
        }
        
        return receiver;
    }
    
    public void bootFlag() {
        if (flagDungeon != null) {
            
            throw new UnsupportedOperationException();
        }
        
        this.flagDungeon = new FlagDungeon(this);
        this.flagDungeon.addTransmitter(this);
        
        // player.registerObserver(this.flagDungeon);
    }
    
    public void linkObsBoulder() {
        List<Boulder> b = new ArrayList<>();
        List<Switch> s = new ArrayList<>();
        
        for(Entity entity : entities) {
            if ((entity instanceof Boulder)) {
                b.add((Boulder) entity);
                
            } else if ((entity instanceof Switch)) {
                
                s.add((Switch) entity);
            }
        }

        for(Boulder boulder : b) {
            for(Switch flswitch : s) {
                
                // TODO boulder.registerObserver(flswitch);
            }
        }
    }
    
    public void linkObsExit() {
        Player pl = null;
        Exit ex = null;
        
        for(Entity entity : entities) {
            if (entity instanceof Player) {
                pl = (Player) entity;
                
            } else if (entity instanceof Exit) {
                
                ex = (Exit) entity;
            }
        }

        if (ex == null || pl == null) {
            
            return;
        }
        
        System.out.println("adding the exit - player observer");
        
        // TODO player.registerObserver(ex);
    }
    
    public void alertObsBoulders() {
        for(Entity ent : entities) {
            if ((ent instanceof Boulder)) {
                
                ((Boulder) ent).notifyObservers();
            }
        }
    }
    
    public Exit obtainExit() {
        for(Entity ent : entities) {
            if (ent instanceof Exit) {
                
                return ((Exit) ent);
            }
        }
        
        return null;
    }
    
    public ArrayList<Enemy> obtainEnemies() {
        ArrayList<Enemy> threats = new ArrayList<>();
        
        for(Entity ent : entities) {
            if (ent instanceof Enemy) {
                
                threats.add((Enemy) ent);
            }
        }
        
        return threats;
    }
    
    public ArrayList<Treasure> obtainTres() {
        ArrayList<Treasure> money = new ArrayList<>();
        
        for(Entity ent : entities) {
            if (ent instanceof Treasure) {
                
                money.add((Treasure) ent);
            }
        }
        
        return money;
    }
    
    public ArrayList<Switch> obtainSwitch() {
        ArrayList<Switch> flswitch = new ArrayList<>();
        
        for(Entity ent : entities) {
            if (ent instanceof Switch) {
                
                flswitch.add((Switch) ent);
            }
        }
        
        return flswitch;
    }
    
    
    
    public List<Entity> getEntityList() {
    	return entities;
    }
    
    public Inventory getInventory() {
    	return inventory;
    }
    
    public boolean getStatus() {
    	return status;
    }
    
    public void collectSword(Sword s) {
    	inventory.collectSword(s);
    }
    
    public void collectTreasure(Treasure t) {
    	inventory.collectTreasure(t);
    	player.getInventory().collectTreasure(t);
    }
    
    public void collectKey(Key k) {
    	inventory.collectKey(k);
    }
    
    public void killPlayer() {
    	this.status = false;
    }
    
    public void endGame() {
    	this.endGame = true;
    }
    
    public boolean isGameOver() {
    	for (Entity e : entities) {
    		if (e instanceof Exit) {
    			Player player = getPlayer();
    			if (player.getX() == e.getX() && player.getY() == e.getY()) {
    				endGame();
    				break;
    			}
    		}
    	}
    	return endGame;
    }

    public List<Entity> obtainBlocked(int x, int y) {
        // TODO Auto-generated method stub
        
        return null;
    }
}
