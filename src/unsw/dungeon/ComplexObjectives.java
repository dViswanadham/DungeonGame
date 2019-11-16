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
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ComplexObjectives extends Goals {
    private String type;
    private List<Goals> objectives;
    private BooleanProperty completedObj;
    
    public ComplexObjectives(String type) {
        // Type is "AND" or "OR" for complex objectives
        this.type = type;
        this.objectives = new ArrayList<>();
        this.completedObj = new SimpleBooleanProperty(false);
    }

    /**
     * Function appends goals & relevant listener for completedObj of the minigoals
     */
    public void append(Goals goal) {
        objectives.add(goal);
        
        goal.completedObj().addListener((event) -> {
            // Get completedObj everytime the goals are changed
            completedObj();
        });
    }

    /**
     * Function discards goal for the complex objectives
     */
    public void discard(Goals goal) {
        
        objectives.add(goal);
    }
    
    /**
     * Obtain the objectives of the goal
     * 
     * @return objectives (List<Goals>)
     */
    
    public List<Goals> obtainObjectives() {
        
        return this.objectives;
    }

    /**
     * Function determines whether the progress of these complex objectives is 
     * true or false and returns the relevant bool property
     * 
     * @return BooleanProperty
     */
    public BooleanProperty completedObj() {
        if (this.objectives.size() == 0)
            return completedObj;
        
        boolean progress = true;

        if (type.equals("AND")) {
            for(Goals g : objectives) {
                if (!g.completedObj().get())
                    
                    progress = false;
            }
            
        } else {
            progress = false;
            
            for(Goals g : objectives) {
                if (g.completedObj().get())
                    
                    progress = true;
            }
        }
        
        completedObj.set(progress);

        return completedObj;
    }
}