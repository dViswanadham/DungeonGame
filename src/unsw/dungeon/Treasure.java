/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

public class Treasure extends Token implements GoalsObservable{
    private GoalsObserver obsTreasure = null;
    private boolean take;
	
    public Treasure(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.take = false;
    }
    
    public boolean take() {
        
        return this.take;
    }
    
    /**
     * Function attempts to detect collisions with other entities. 
     * An entity which isn't a player_object will pass through the treasure
     * whilst a player_object would collect the treasure.
     * 
     * @return boolean 
     */
    public boolean scanSquare(Mobile object) {
        if (!(object instanceof Player)) {
            
            return true;
        }
        
        Inventory inventory = ((Player) object).getInventory();
        
        inventory.collectTreasure(this);
        this.take = true;
        
        System.out.println("Treasure collected.");
        
        notifyObservers();

        return true;
    }

    @Override
    public void registerObserver(GoalsObserver observer) {
        obsTreasure = observer;
        observer.appendObs(this);
    }

    @Override
    public void removeObserver(GoalsObserver observer) {
        if (obsTreasure == observer) {
            obsTreasure.deleteObs(this);
            
            obsTreasure = null;
        }
    }

    @Override
    public void notifyObservers() {
        
        obsTreasure.refresh(this);
    }
    
    @Override
    public boolean collectObject(Inventory inventory) {
    	inventory.collectTreasure(this);
        getDungeon().removeEntity(this);
        
    	return true;
    }
}