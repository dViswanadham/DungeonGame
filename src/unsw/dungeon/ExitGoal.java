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
 * Implements the ExitGoal that must be completedObj in order to finish a dungeon if presribed
 */
import javafx.beans.property.SimpleBooleanProperty;

public class ExitGoal extends Goals implements Observer {
    private String type;
    private SimpleBooleanProperty completedObj;
    
    public ExitGoal(String type) {
        this.type = type;
        
        this.completedObj = new SimpleBooleanProperty(false);
    }
    
    public String obtainType() {
        
        return this.type;
    }

    public void append(Goals goal) {
        
        throw new UnsupportedOperationException();
    }

    public void discard(Goals goal) {
        
        throw new UnsupportedOperationException();
    }

    public SimpleBooleanProperty completed() {
        
        return completedObj;
    }

    @Override
    public void refresh(Observable obj) {
        completedObj.set(((Exit) obj).obtainCondition());
        
        if (completedObj.get()) {
            System.out.println("ExitGoal completed");
        }
    }
}