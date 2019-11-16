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

public class Boulder extends Mobile {
	
    public Boulder(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }

    @Override
    public boolean isBlocking() {
    	return true;
    }
    
    public boolean isActivated() {
    	List<Entity> entities = getDungeon().getEntityList();
    	for (Entity e : entities) {
    		if (e instanceof Switch) {
    			if (e.getX() == getX() && e.getY() == getY()) {
    				Switch s = (Switch) e; 
    				s.isActivated();
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
