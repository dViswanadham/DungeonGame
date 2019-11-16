/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * Implements the floor switch goal as detailed in the spec to complete or partially complete a dungeon
 * @author z5161251
 *
 */
public class SwitchGoal extends Goals implements GoalsObserver {
    private String name;
    private SimpleBooleanProperty achieved;
    private ArrayList<GoalsObservable> subjects;
    private SimpleIntegerProperty progress;
    private SimpleIntegerProperty total;
    
    public SwitchGoal(String name) {
        this.name = name;
        this.progress = new SimpleIntegerProperty(0);
        this.achieved = new SimpleBooleanProperty(false);
        this.subjects = new ArrayList<GoalsObservable>();
        this.total = new SimpleIntegerProperty(subjects.size());
    }
    
    public SimpleIntegerProperty getProgress() {
        return this.progress;
    }
    
    public SimpleIntegerProperty getTotal() {
        return this.total;
    }
    
    public void setProgress(int p) {        
        this.progress.set(p);
    }
    
    public void add(Goals goal) {
        throw new UnsupportedOperationException();
    }

    public void remove(Goals goal) {
        throw new UnsupportedOperationException();
    }
    
    public String getName() {
        return this.name;
    }

    public SimpleBooleanProperty achieved() {
        return achieved;
    }

    /**
     * Checks to see whether all floor switches are in their 'on' state
     */
    @Override
    public void update(GoalsObservable obj) {
        boolean done = true;
        int count = 0;
        
        for (GoalsObservable fs : subjects) {
            if (((Switch) fs).isActivated() != true) {
                done = false;
            
            } else {
                
                count++;
            }
        }
        
        setProgress(count);
        
        achieved.set(done);
        
        if (achieved.get()) {
            
            System.out.println("Switch goal has been completed.");
        }
    }

    @Override
    public void appendObs(GoalsObservable obj) {
        subjects.add(obj);
        System.out.println("Object added: " + obj.getClass());
        this.total.set(subjects.size());
    }

    @Override
    public void deleteObs(GoalsObservable obj) {
        if (subjects.contains(obj))
            subjects.remove(obj);
        this.total.set(subjects.size());
    }
}