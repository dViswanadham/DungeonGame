/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public interface Observable {
	
	public void registerObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifyObservers();
	
}
