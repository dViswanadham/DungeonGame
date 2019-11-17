/**
 * Team: Desktop_Support
 * Members: Liam (z5207407) and Dheeraj (z5204820)
 * 
 * Started: 24/10/2019 | Last edited: 14/11/2019
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
        
        Sword sword1 = new Sword(0, 0, dungeon);
        Sword sword2 = new Sword(2, 0, dungeon);
        
        InvincibilityPotion potion = new InvincibilityPotion(2, 0, dungeon);
        
        dungeon.addEntity(sword1);
        dungeon.addEntity(sword2);
        dungeon.addEntity(potion);
        
        Enemy f1 = new Enemy(dungeon, 0, 1);
        Enemy f2 = new Enemy(dungeon, 1, 1);
        Enemy f3 = new Enemy(dungeon, 2, 1);
        Enemy f4 = new Enemy(dungeon, 2, 1);
        Enemy f5 = new Enemy(dungeon, 2, 1);
        Enemy f6 = new Enemy(dungeon, 2, 1);
        Enemy f7 = new Enemy(dungeon, 2, 1);
        Enemy f8 = new Enemy(dungeon, 2, 1);
        
        dungeon.addEntity(f1);
        dungeon.addEntity(f2);
        dungeon.addEntity(f3);
        dungeon.addEntity(f4);
        dungeon.addEntity(f5);
        dungeon.addEntity(f6);
        dungeon.addEntity(f7);
        dungeon.addEntity(f8);
        
        q1.move(Direction.DOWN);
        q2.move(Direction.DOWN);
        q3.move(Direction.DOWN);
        assertTrue(q1.isActive());
        assertTrue(q2.isActive());
        assertTrue(q3.isActive());
        assertTrue(sword1 != null);
    }
    
    @Test
    void test2() {
        // Interactions - player_object, boulder, foe
        Dungeon dungeon = new Dungeon(3, 1);
        Player q1 = new Player(dungeon, 0, 0);
        
        dungeon.addEntity(q1);
        
        Boulder b1 = new Boulder(1, 0, dungeon);
        
        dungeon.addEntity(b1);
        
        Enemy e1 = new Enemy(dungeon, 2, 0);
        
        dungeon.addEntity(e1);
        q1.move(Direction.RIGHT);
        
        assertTrue(q1.getX() == 1);
        assertTrue(b1.getX() == 1);
        assertTrue(e1.getX() == 2);
    }
    
    @Test
    void test3() {
        // Interactions - player_object, foe, sword
        Dungeon dungeon = new Dungeon(3, 2);
        // pl_object one & two have invincibility potion, but two's time runs out before they move down
        // pl_object 3 has the invincibility potion on te enemy's cell.
        Player q1 = new Player(dungeon, 0, 0);
        Player q2 = new Player(dungeon, 1, 0);
        Player q3 = new Player(dungeon, 1, 0);
        
        dungeon.addEntity(q1);
        dungeon.addEntity(q2);
        dungeon.addEntity(q3);
        
        InvincibilityPotion p1 = new InvincibilityPotion(0, 0, dungeon);
        InvincibilityPotion p2 = new InvincibilityPotion(1, 0, dungeon);
        InvincibilityPotion p3 = new InvincibilityPotion(2, 1, dungeon);
        
        dungeon.addEntity(p1);
        dungeon.addEntity(p2);
        dungeon.addEntity(p3);
        
        Enemy f1 = new Enemy(dungeon, 0, 1);
        Enemy f2 = new Enemy(dungeon, 1, 1);
        Enemy f3 = new Enemy(dungeon, 2, 1);
        
        dungeon.addEntity(f1);
        dungeon.addEntity(f2);
        dungeon.addEntity(f3);
        
        for(int b = 0; b < 10; b = (b + 1)) {
            
            p2.getDuration();
        }
        
        q1.move(Direction.DOWN);
        q2.move(Direction.DOWN);
        q3.move(Direction.DOWN);
        
        assertTrue(q1.isActive());
        assertTrue(q2.isActive());
        assertTrue(q3.isActive());
//        assertEquals(f1.active(), false);
//        assertEquals(f2.active(), true);
//        assertEquals(f3.active(), true);
    }
}