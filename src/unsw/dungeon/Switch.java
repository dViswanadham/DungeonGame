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

public class Switch extends Entity implements Observer, GoalsObservable {
	private GoalsObserver switchObserver = null;
    
    public Switch(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }
    
    public boolean isActivated() {
    	List<Entity> entities = getDungeon().getEntityList();
    	for (Entity e : entities) {
    		if (e instanceof Boulder) {
    			if (e.getX() == getX() && e.getY() == getY()) {
    				System.out.println("activated");
    				return true;
    			}
    		}
    	}
    	return false;
    }

    @Override
    public void registerObserver(GoalsObserver obs) {
        switchObserver = obs;
        obs.appendObs(this);
    }

    @Override
    public void removeObserver(GoalsObserver obs) {
        if (switchObserver == obs) {
            switchObserver.deleteObs(this);
            switchObserver = null;
        }
        
    }

    @Override
    public void refresh(Observable e) {
        if (switchObserver != null) {
            switchObserver.refresh(this);
        }
    }
}