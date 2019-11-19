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

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Enemy extends Mobile implements GoalsObservable, FlagDungeonClient {
    private GoalsObserver enemyObserver = null;
    private int moveCount;

    /**
     * @param dungeon
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, dungeon);
        this.moveCount = 0;
    }
    
    /**
     *
     */
    @Override
    public boolean isBlocking() {
    	return true;
    }

    /**
     * 
     */
    public void moveUp() {
    	Dungeon dungeon = getDungeon();
    	Player player = getDungeon().getPlayer();
        if (getY() <= 0) {
            return;
        }
        
        if (blocked(getX(), getY() - 1)) {
            y().set(getY() - 1);
            int eX = player.getX() - getX();
            int eY = player.getY() - getY();
            if (eX == 0 && eY == 0) {
            	blockingEntityBehaviour(dungeon, player.getInventory(), this);
            }
        }
    }

    /**
     * 
     */
    public void moveDown() {
    	Dungeon dungeon = getDungeon();
    	Player player = getDungeon().getPlayer();
        if (getY() >= getDungeon().getHeight() - 1) {
            return;
        }
        
        if (blocked(getX(), getY() + 1)) {
            y().set(getY() + 1);
            int eX = player.getX() - getX();
            int eY = player.getY() - getY();
            if (eX == 0 && eY == 0) {
            	blockingEntityBehaviour(dungeon, player.getInventory(), this);
            }
        }
    }

    /**
     * 
     */
    public void moveLeft() {
    	Dungeon dungeon = getDungeon();
    	Player player = getDungeon().getPlayer();
        if (getX() <= 0) {
            return;
        }
        if (blocked(getX() - 1, getY())) {
            x().set(getX() - 1);
            int eX = player.getX() - getX();
            int eY = player.getY() - getY();
            if (eX == 0 && eY == 0) {
            	blockingEntityBehaviour(dungeon, player.getInventory(), this);
            }
        }
    }

    /**
     * 
     */
    public void moveRight() {
    	Dungeon dungeon = getDungeon();
        if (getX() >= dungeon.getWidth() - 1) {
            return;
        }
        Player player = dungeon.getPlayer();
        if (blocked(getX() + 1, getY())) {
            x().set(getX() + 1);
            int eX = player.getX() - getX();
            int eY = player.getY() - getY();
            if (eX == 0 && eY == 0) {
            	blockingEntityBehaviour(dungeon, player.getInventory(), this);
            }
        }
    }
    
    /**
     * checks if a given x, y position has a blocked entity on it
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean blocked(int x, int y) {
    	Dungeon dungeon = getDungeon();
        List<Entity> blocking = dungeon.obtainTargetSquare(x, y);
        if (blocking == null) {
            return true;
        }
        for(Entity e : blocking) {
            if (e.isBlocking()) {
            	if (e instanceof Player) {
            		if (this.isActive()) {
            			return true;
            		} else {
            			dead();
            		}
            	}
                return false;
            }
        }
        
        return true;
    }

    /**
     *
     */
    @Override
    public void registerObserver(GoalsObserver o) {
        enemyObserver = o;
        o.appendObs(this);
    }

    /**
     *
     */
    @Override
    public void removeObserver(GoalsObserver o) {
        if (enemyObserver == o) {
            enemyObserver.deleteObs(this);
            enemyObserver = null;
        }
    }

    /**
     *
     */
    @Override
    public void notifyObservers() {
        if (enemyObserver != null) {
            enemyObserver.refresh(this);
        }
    }
    
    /**
     * Enemy follows player and can move diagonally. Updated when player status changes.
     */
    public void huntPlayer() {
    	Dungeon dungeon = getDungeon();
        Player player = dungeon.getPlayer();
        int eX = player.getX() - getX();
        int eY = player.getY() - getY();
        if (player.getInvincibleStatus()) {
            if (eX > 0) {
                moveLeft();
            }
            
            if (eX < 0) {
                moveRight();
            }
                
            if (eY > 0) {
                moveUp();
            }
            if (eY < 0) {
                moveDown();
            }
        } else {
            if (eX > 0) {
                moveRight();
            }
            
            if (eX < 0) {
                moveLeft();
            }
                
            if (eY > 0) {
                moveDown();
            }
            
            if (eY < 0) {
                moveUp();
            }
        }
    }

    /**
     *
     */
    @Override
    public void flag() {
        this.moveCount++;
        
        // can adjust enemy speed as required
        if (this.moveCount % 2 == 0) {
            huntPlayer();
        }
    }
}