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
    private String name;
    private List<Goals> subgoals;
    private BooleanProperty achieved;
    
    public ComplexObjectives(String name) {
        // name should be either "AND" or "OR" for MultipleGoals
        this.name = name;
        this.subgoals = new ArrayList<>();
        this.achieved = new SimpleBooleanProperty(false);
    }

    /**
     * Add subgoal for this multiplegoals and attaching a listener for achieved state of the subgoal
     */
    public void add(Goals goal) {
        subgoals.add(goal);
        goal.achieved().addListener((event) -> {
            // Call achieved on any change on subgoal
            achieved();
        });
    }

    /**
     * Remove subgoal for this multiplegoals
     */
    public void remove(Goals goal) {
        subgoals.add(goal);
    }
    
    /**
     * Get subgoals of this multiplegoals
     * @return subgoals (List<DungeonGoals>)
     */
    public List<Goals> getGoals() {
        return this.subgoals;
    }

    /**
     * Checks if the status of this multipleGoals is true/false and returns the booleanproperty of the achieved state
     * @return BooleanProperty
     */
    public BooleanProperty achieved() {
        if (this.subgoals.size() == 0)
            return achieved;
        
        boolean tempStatus = true;

        if (name.equals("AND")) {
            for (Goals a : subgoals) {
                if (!a.achieved().get())
                    tempStatus = false;
            }
        } else {
            tempStatus = false;
            for (Goals a : subgoals) {
                if (a.achieved().get())
                    tempStatus = true;
            }
        }
        
        achieved.set(tempStatus);

        return achieved;
    }
}