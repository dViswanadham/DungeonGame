/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Portal extends Entity {
	
	private int id;
	
    /**
     * @param x
     * @param y
     * @param dungeon
     * @param id
     */
    public Portal(int x, int y, Dungeon dungeon, int id) {
        super(x, y, dungeon);
        this.id = id;
    }
    
    /**
     * @return
     */
    public int getID() {
    	return id;
    }
    
    /**
     * @param newID
     */
    public void setID(int newID) {
    	this.id = newID;
    }
}
