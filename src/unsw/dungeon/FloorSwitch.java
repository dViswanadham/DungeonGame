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

public class FloorSwitch extends Entity {

	private int x, y;
	private Dungeon dungeon;
	
    public FloorSwitch(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.x = x;
        this.y = y;
        this.dungeon = dungeon;
    }

    public boolean isActivated() {
    	List<Entity> entities = dungeon.getEntityList();
    	for (Entity e : entities) {
    		if (e instanceof Boulder || e instanceof Player) {
    			if (e.getX() == x && e.getY() == y) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
