/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Exit extends Entity implements Observer, Observable {
    private boolean exitPl = false;
    private Observer exitObs = null;
    
    /**
     * @param x
     * @param y
     * @param dungeon
     */
    public Exit(int x, int y, Dungeon dungeon) {
        
        super(x, y, dungeon);
    }
    
    /**
     * Function is used to alert observers when the player_object is on the exit
     */
    /**
     *
     */
    @Override
    public void refresh(Observable o) {
        if (!(o instanceof Player)) {
            
            return;
        }
        
        Player pl = (Player) o;
        
        if (pl.getX() == getX() && pl.getY() == getY()) {
            exitPl = true;
            notifyObservers();
            
        } else {
            exitPl = false;
            notifyObservers();
        }
    }

    /**
     * @return
     */
    public boolean obtainCondition() {
        
        return exitPl;
    }

    /**
     *
     */
    @Override
    public void registerObserver(Observer observer) {
        System.out.println("Catalogued the observer " + observer.getClass());
        
        exitObs = observer;
    }

    /**
     *
     */
    @Override
    public void removeObserver(Observer observer) {
        if (exitObs == observer) {
            
            exitObs = null;
        }
    }

    /**
     *
     */
    @Override
    public void notifyObservers() {
        if (exitObs != null) {
            
            exitObs.refresh(this);
        }
    }
}