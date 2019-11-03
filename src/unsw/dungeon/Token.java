package unsw.dungeon;

public class Token extends Entity {
	
	private Inventory inventory;
	
	public Token(int x, int y) {
        super(x,y);
	}
	
	public boolean createInventory() {
		inventory = new Inventory();
		return true;
	}

	public boolean depositInventory() {
		
	}

    public boolean collectObject(Dungeon dungeon, Inventory inventory) {
    	inventory.addObject(this);
    	Dungeon.removeObject(this);
    	notifyObservers();
    	return true;
    }
}
