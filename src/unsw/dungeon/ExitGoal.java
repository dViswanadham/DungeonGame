/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;
/**
 * Implements the ExitGoal that must be achieved in order to finish a dungeon if presribed
 */
import javafx.beans.property.SimpleBooleanProperty;

public class ExitGoal extends Goals implements Observer {
    private String name;
    private SimpleBooleanProperty achieved;

    public ExitGoal(String name) {
        // name should be either "AND" or "OR" for MultipleGoals
        this.name = name;
        this.achieved = new SimpleBooleanProperty(false);
    }
    
    public String getName() {
        return this.name;
    }

    public void add(Goals goal) {
        throw new UnsupportedOperationException();
    }

    public void remove(Goals goal) {
        throw new UnsupportedOperationException();
    }

    public SimpleBooleanProperty achieved() {
        return achieved;
    }

    @Override
    public void update(Observable obj) {
        achieved.set(((Exit) obj).getStatus());
        if (achieved.get())
            System.out.println("ExitGoal has been achieved");
    }
}