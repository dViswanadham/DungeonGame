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

public class EnemyGoal extends Goals implements GoalsObserver {
    private String type;
    private SimpleBooleanProperty completedObj;
    private ArrayList<GoalsObservable> observers;
    private SimpleIntegerProperty checkpoint;
    private SimpleIntegerProperty sum;
    
    public EnemyGoal(String type) {
        this.type = type;
        this.completedObj = new SimpleBooleanProperty(false);
        this.checkpoint = new SimpleIntegerProperty(0);
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
    
    /**
     * Function determines whether the goal has been completed yet
     * and sees if the observable objects are alive
     */
    @Override
    public void update(GoalsObservable ent) {
        boolean complete = true;
        int iterator = 0;
        
        for(GoalsObservable enemy : observers) {
            if (((Enemy) enemy).isActive()) {
                complete = false;
            
            } else {
                
                iterator++;
            }
        }
        
        applyCheckpoint(iterator);
        
        completedObj.set(complete);
        
        if (completedObj.get()) {
            
            System.out.println("The Enemy Goal is completed.");
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