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

public class DungeonObjectives {
    
	private Dungeon dungeon;
	private BooleanProperty status;
	private List<DungeonObjectives> subGoals;

	public DungeonObjectives(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.status = new SimpleBooleanProperty(false);
		this.subGoals = new ArrayList<DungeonObjectives>();
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
	
	public void addSubGoal(DungeonObjectives goal) {
		subGoals.add(goal);
	}
	
	public void removeSubGoal(DungeonObjectives goal) {
		subGoals.remove(goal);
	}
	
	public List<DungeonObjectives> getSubGoalList() {
		return subGoals;
	}
	
}