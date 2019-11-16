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
        // type should be either "AND" or "OR" for MultipleGoals
        this.type = type;
        this.objectives = new ArrayList<>();
        this.completedObj = new SimpleBooleanProperty(false);
    }

    /**
     * Add subgoal for this multiplegoals and attaching a listener for completedObj state of the subgoal
     */
    public void append(Goals goal) {
        objectives.add(goal);
        goal.completedObj().addListener((event) -> {
            // Call completedObj on any change on subgoal
            completedObj();
        });
    }

    /**
     * Remove subgoal for this multiplegoals
     */
    public void remove(Goals goal) {
        objectives.add(goal);
    }
    
    /**
     * Get objectives of this multiplegoals
     * @return objectives (List<DungeonGoals>)
     */
    public List<Goals> getGoals() {
        return this.objectives;
    }

    /**
     * Checks if the status of this multipleGoals is true/false and returns the booleanproperty of the completedObj state
     * @return BooleanProperty
     */
    public BooleanProperty completedObj() {
        if (this.objectives.size() == 0)
            return completedObj;
        
        boolean tempStatus = true;

        if (type.equals("AND")) {
            for (Goals a : objectives) {
                if (!a.completedObj().get())
                    tempStatus = false;
            }
        } else {
            tempStatus = false;
            for (Goals a : objectives) {
                if (a.completedObj().get())
                    tempStatus = true;
            }
        }
        
        completedObj.set(tempStatus);

        return completedObj;
    }
}