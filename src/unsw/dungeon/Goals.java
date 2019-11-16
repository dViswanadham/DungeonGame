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

public class Goals {
    
	private Dungeon dungeon;
	private BooleanProperty status;
	private List<Goals> subGoals;

	public Goals(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.status = new SimpleBooleanProperty(false);
		this.subGoals = new ArrayList<Goals>();
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public boolean isSatisfied() {
		return status.get();
	}
	
	public BooleanProperty getSatisfiedProperty() {
		return status;
	}
	
	public void addSubGoal(Goals goal) {
		subGoals.add(goal);
	}
	
	public void removeSubGoal(Goals goal) {
		subGoals.remove(goal);
	}
	
	public List<Goals> getSubGoalList() {
		return subGoals;
	}
	
}