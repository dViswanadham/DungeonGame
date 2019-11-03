package unsw.dungeon;

public class Token extends Entity {
	
	public Token(int x, int y) {
        super(x,y);
	}
	
	public boolean createInventory() {
		
		return false;
	}

}
