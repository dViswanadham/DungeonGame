/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Wall extends Entity {
	
    /**
     * @param x
     * @param y
     * @param dungeon
     */
    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    /**
     *
     */
    @Override
    public boolean isBlocking() {
    	return true;
    }
}
