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

    public Enemy(Dungeon dungeon, int x, int y) {
        super(y, x, dungeon);
        
        this.moveCount = 0;
    }
    
    @Override
    public boolean isBlocking() {
    	return true;
    }

    public void moveUp() {
        if (getY() <= 0) {
            
            return;
        }
        
        if (blocked(getX(), getY() - 1)) {
            
            y().set(getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() >= getDungeon().getHeight() - 1) {
            
            return;
        }
        
        if (blocked(getX(), getY() + 1)) {
            
            y().set(getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() <= 0) {
            
            return;
        }
        
        if (blocked(getX() - 1, getY())) {
            
            x().set(getX() - 1);
        }
    }

    public void moveRight() {
        if (getX() >= getDungeon().getWidth() - 1) {
            
            return;
        }
        
        if (blocked(getX() + 1, getY())) {
            
            x().set(getX() + 1);
        }
    }
    
    public boolean blocked(int x, int y) {
        List<Entity> colliding = getDungeon().obtainTargetSquare(x, y);
        
        if (colliding == null) {
            
            return true;
        }
        
        for(Entity f : colliding) {
            if (!f.isBlocking()) {
                
                return false;
            }
        }
        
        return true;
    }

    public boolean scanSquare(Mobile e) {
        if (e instanceof Enemy) {
            
            return true;
        }
        
        if (!(e instanceof Player)) {
            
            return true;
        }
        
        Player p = (Player) e;
        
        if (!p.getInvincibleStatus()) {
            System.out.println("1");
            p.dead();
            
            return false;
            
        } else {
            dead();
            
            notifyObservers();
        }
        
        return true;
    }

    @Override
    public void registerObserver(GoalsObserver o) {
        enemyObserver = o;
        
        o.appendObs(this);
    }

    @Override
    public void removeObserver(GoalsObserver o) {
        if (enemyObserver == o) {
            enemyObserver.deleteObs(this);
            
            enemyObserver = null;
        }
    }

    @Override
    public void notifyObservers() {
        if (enemyObserver != null) {
            
            enemyObserver.refresh(this);
        }
    }
    
    public void huntPlayer() {
        Player pl = getDungeon().getPlayer();
        
        int eX = pl.getX() - getX();
        int eY = pl.getY() - getY();

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

    @Override
    public void flag() {
        this.moveCount++;
        
        // can adjust enemy speed as required
        if (this.moveCount % 1 == 0) {
            huntPlayer();
        }
    }
}