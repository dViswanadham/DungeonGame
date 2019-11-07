/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;
/**
 * Implements the Mobile abstract class, used with entities in the dungeon that are moveable
 */
public abstract class Mobile extends Entity {

    private Dungeon dungeon = null;
    private boolean active;

    public Mobile(Dungeon dungeon, int x, int y) {
        super(x, y);
        
        this.dungeon = dungeon;
        this.active = true;
    }

    public Dungeon dungeon() {
        
        return this.dungeon;
    }

    public void dead() {
        this.triggerSeeable(false);
        
        dungeon.removeEntity(this);
        
        this.active = false;
    }

    public boolean active() {
        
        return this.active;
    }
    
    public void createActive(boolean active) {
        
        this.active = active;
    }

    /**
     * 
     * Function scans for any interaction against a token
     * 
     * @param object
     * @return boolean
     */
    public boolean scanSquare(Token object) {
        
        return false;
    }
    
    /**
     * 
     * Function scans for any interaction against a barrier
     * 
     * @param object
     * @return boolean
     */
    public boolean scanSquare(Wall object) {
        
        return false;
    }

    
    /**
     * 
     * Function scans for any interaction against a mobile entity (e.g. foes)
     * 
     * @param object
     * @return boolean
     */
    public boolean scanSquare(Mobile object) {
        
        return false;
    }
}