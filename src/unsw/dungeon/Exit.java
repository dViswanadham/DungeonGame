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
    private boolean playerExit = false;
    private Observer exitObserver = null;
    
    public Exit(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
    }
    /**
     * Used to notify oberservers of when a player is on the Exit
     */
    @Override
    public void update(Observable obj) {
        if (!(obj instanceof Player))
            return;
        Player player = (Player) obj;
        if (player.getX() == getX() && player.getY() == getY()) {
            playerExit = true;
            notifyObservers();
        } else {
            playerExit = false;
            notifyObservers();
        }
    }

    public boolean getStatus() {
        return playerExit;
    }

    @Override
    public void registerObserver(Observer o) {
        System.out.println("Registered observer " + o.getClass());
        exitObserver = o;
    }

    @Override
    public void removeObserver(Observer o) {
        if (exitObserver == o) {
            exitObserver = null;
        }
    }

    @Override
    public void notifyObservers() {
        if (exitObserver != null)
            exitObserver.update(this);
    }
}
