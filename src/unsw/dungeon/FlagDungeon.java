/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 15/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import java.util.ArrayList;

public class FlagDungeon implements Observer {
    private ArrayList<FlagDungeonClient> receiver;
    private int iterator;
    
    public FlagDungeon(Dungeon dungeon) {
        
        this.iterator = 0;
    }

    public void addTransmitter(Dungeon dungeon) {
        
        this.receiver = dungeon.obtainSignal();
    }

    @Override
    public void refresh(Observable e) {
        this.iterator++;
        
        for (FlagDungeonClient sub : receiver) {
            sub.flag();
        }
    }
    
    

}