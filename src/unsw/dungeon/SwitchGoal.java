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

public class SwitchGoal extends Goals implements GoalsObserver {
    private String type;
    private SimpleBooleanProperty completedObj;
    private ArrayList<GoalsObservable> observers;
    private SimpleIntegerProperty checkpoint;
    private SimpleIntegerProperty sum;
    
    public SwitchGoal(String type) {
        this.type = type;
        this.checkpoint = new SimpleIntegerProperty(0);
        this.completedObj = new SimpleBooleanProperty(false);
        this.observers = new ArrayList<GoalsObservable>();
        this.sum = new SimpleIntegerProperty(observers.size());
    }
    
    public SimpleIntegerProperty obtainCheckpoint() {
        
        return this.checkpoint;
    }
    
    public SimpleIntegerProperty obtainSum() {
        
        return this.sum;
    }
    
    public void applyCheckpoint(int c) {   
        
        this.checkpoint.set(c);
    }
    
    public void append(Goals goal) {
        
        throw new UnsupportedOperationException();
    }

    public void discard(Goals goal) {
        
        throw new UnsupportedOperationException();
    }
    
    public String obtainType() {
        
        return this.type;
    }

    public SimpleBooleanProperty completed() {
        
        return completedObj;
    }

    /**
     * Function determines whether the switches are activated or not.
     */
    @Override
    public void update(GoalsObservable obj) {
        boolean complete = true;
        int iterator = 0;
        
        for(GoalsObservable fl_switch : observers) {
            if (((Switch) fl_switch).isActivated() != true) {
                complete = false;
            
            } else {
                
                iterator++;
            }
        }
        
        applyCheckpoint(iterator);
        
        completedObj.set(complete);
        
        if (completedObj.get()) {
            
            System.out.println("The Switch Goal is completed.");
        }
    }
    
    @Override
    public void appendObs(GoalsObservable obj) {
        observers.add(obj);
        System.out.println("The following obj is attached: " + obj.getClass());
        
        this.sum.set(observers.size());
    }

    @Override
    public void deleteObs(GoalsObservable obj) {
        if (observers.contains(obj)) {
            
            observers.remove(obj);
        }
        
        this.sum.set(observers.size());
    }
}