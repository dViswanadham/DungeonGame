/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 3/11/2019
 * 
 * Acknowledgement: some of the code may be similar to the lab code.
 */


package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Testing {

    @Test
    void test1() {
        // Interactions - player_object, foe, invincibility_potion, sword
        Dungeon dungeon = new Dungeon(5, 6);
        
        Player q1 = new Player(dungeon, 1, 2);
        Player q2 = new Player(dungeon, 0, 4);
        Player q3 = new Player(dungeon, 3, 3);
        
        dungeon.addEntity(q1);
        dungeon.addEntity(q2);
        dungeon.addEntity(q3);
        
        Sword sword1 = new Sword(0,0);
        Sword sword2 = new Sword(2,0);
        
        InvincibilityPotion potion = new InvincibilityPotion(2,0);
        
        dungeon.addEntity(sword1);
        dungeon.addEntity(sword2);
        dungeon.addEntity(potion);
        
        q1.blocked(0, 0);
        q3.blocked(2, 0);
        
        Foe f1 = new Foe(dungeon, 0, 1);
        Foe f2 = new Foe(dungeon, 1, 1);
        Foe f3 = new Foe(dungeon, 2, 1);
        Foe f4 = new Foe(dungeon, 2, 1);
        Foe f5 = new Foe(dungeon, 2, 1);
        Foe f6 = new Foe(dungeon, 2, 1);
        Foe f7 = new Foe(dungeon, 2, 1);
        Foe f8 = new Foe(dungeon, 2, 1);
        
        dungeon.addEntity(f1);
        dungeon.addEntity(f2);
        dungeon.addEntity(f3);
        dungeon.addEntity(f4);
        dungeon.addEntity(f5);
        dungeon.addEntity(f6);
        dungeon.addEntity(f7);
        dungeon.addEntity(f8);
        
        q1.moveDown();
        q2.moveDown();
        q3.moveDown();
        
        assertEquals(q1.active(), true);
//        assertEquals(q2.active(), false);
        assertEquals(q3.active(), true);
        assertTrue(sword1 != null);
    }
    
    @Test
    void test2() {
        // Collision method between player-boulder-enemy or player-boulder-consumable
        Dungeon dungeon = new Dungeon(3, 1);
        Player q1 = new Player(dungeon, 0, 0);
        
        dungeon.addEntity(q1);
        
        Boulder b1 = new Boulder(1, 0);
        
        dungeon.addEntity(b1);
        
        Foe e1 = new Foe(dungeon, 2, 0);
        
        dungeon.addEntity(e1);
        q1.moveRight();
        
        assertTrue(q1.getX() == 1);
        assertTrue(b1.getX() == 1);
        assertTrue(e1.getX() == 2);
    }
    
    @Test
    void test3() {
        // Collision method between player enemy and sword
        // Game ticker is not enabled for testing purpose
        Dungeon dungeon = new Dungeon(3, 2);
        // Player 1 and 2 gets both potion, but 2 expires before moving down
        // Player 3 has potion on enemy cell
        Player q1 = new Player(dungeon, 0, 0);
        Player q2 = new Player(dungeon, 1, 0);
        Player q3 = new Player(dungeon, 1, 0);
        
        dungeon.addEntity(q1);
        dungeon.addEntity(q2);
        dungeon.addEntity(q3);
        
        InvincibilityPotion p1 = new InvincibilityPotion(0, 0);
        InvincibilityPotion p2 = new InvincibilityPotion(1, 0);
        InvincibilityPotion p3 = new InvincibilityPotion(2, 1);
        
        dungeon.addEntity(p1);
        dungeon.addEntity(p2);
        dungeon.addEntity(p3);
        
        q1.blocked(0, 0);
        q2.blocked(1, 0);
        
        Foe f1 = new Foe(dungeon, 0, 1);
        Foe f2 = new Foe(dungeon, 1, 1);
        Foe f3 = new Foe(dungeon, 2, 1);
        
        dungeon.addEntity(f1);
        dungeon.addEntity(f2);
        dungeon.addEntity(f3);
        
        // Make the first potion to expire
        // after 10 tick, the enemy should die
        for(int a = 0; a < 10; a++) {
            
            p2.getDuration();
        }
        
        q1.moveDown();
        q2.moveDown();
        q3.moveDown();
        
        assertEquals(q1.active(), true);
        assertEquals(q2.active(), false);
        assertEquals(q3.active(), false);
//        assertEquals(f1.active(), false);
//        assertEquals(f2.active(), true);
//        assertEquals(f3.active(), true);
    }
}