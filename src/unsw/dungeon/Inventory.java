package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores tokens that the player_object saves
 */
public class Inventory {
    private List<Token> tokenList;
    private Dungeon dungeon;
    private int tokenNum = 1;

    public Inventory() {
        this.tokenList = new ArrayList<>();
        this.dungeon = dungeon;
    }
    
    public List<Token> obtainTokenList() {
        
        return this.tokenList;
    }
    
    public void appendToken(Token token) {
        
        tokenList.add(token);
    }
    
    public void expungeToken(Token token) {
        if (tokenList.contains(token)) {
            ((Entity) token).triggerSeeable(false);
            
            tokenList.remove(token);
            tokenNum = (tokenNum - 1);
            
            sort();
        }
    }
    
    public void discardTokens(Token token) {
        if (tokenList.contains(token)) {
            tokenList.remove(token);
            tokenNum = (tokenNum - 1);
            
            sort();
        }
    }
    
    private void sort() {
        int token = 1;
        
        for(Token x : tokenList) {
            x.y().set(token);
            
            token++;
        }
    }
    
    public int obtainStorageLimit() {
        
        return this.dungeon.getWidth();
    }
    
    /*
    public boolean storeToken(Key k1) {
        for(Token t : tokenList) {
            if (t instanceof Key) {
                
                return false;
            }
        }
        
        // k1.storeBackpack(this);
        // k1.setStorage(obtainStorageLimit(), tokenNum);
        
        tokenNum = (tokenNum + 1);
        
        return true;
    }
    */
    /*
    public boolean storeToken(InvincibilityPotion potion) {
        for(Token t : tokenList) {
            if (t instanceof InvincibilityPotion) {
                
                return false;
            }
        }
       
        potion.storeBackpack(this);
        potion.setStorage(obtainStorageLimit(), tokenNum);
        
        tokenNum = (tokenNum + 1);
        
        return true;
    }
    */
    
    /*
    public boolean storeToken(Sword s) {
        for(Token t : tokenList) {
            if (t instanceof Sword) {
                
                return false;
            }
        }
        
        s.storeBackpack(this);
        s.setStorage(obtainStorageLimit(), tokenNum);
        
        tokenNum = (tokenNum + 1);
        
        return true;
    }
    */
    
    /*
    public boolean storeToken(Treasure t) {
        t.storeBackpack(this);
        t.setStorage(obtainStorageLimit(), tokenNum);
        
        tokenNum = (tokenNum + 1);
        
        return true;
    }
    */
    
    /*
    public boolean applyToken(Door d) {
        for(Token t : tokenList) {
            if (t instanceof Key) {
                // d.switchState();
                Key k1 = (Key) t;
                
                if (k1.appleKey(d)) {
                    
                    return true;
                }
                
                return false;
            }
        }

        return false;
    }
    */
    
    /*
    public boolean applyToken(Enemy t) {
        for(Token potion : tokenList) {
            if (potion instanceof InvincibilityPotion) {
                if (((InvincibilityPotion) potion).stillActive()) {
                    
                    return true;
                }
            }
        }

        for(Token potion : tokenList) {
            if (potion instanceof Sword) {
                if (((Sword) potion).applyWeapon()) {
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    */
    
    /*
    public void discardKey(int x, int y) {
        Key key = null;
        
        for(Token k1 : tokenList) {
            if (k1 instanceof Key) {
                
                key = (Key) k1;
            }
        }
        
        if (key != null) {
            this.discardTokens(key);
            
            key.abandonKey(x, y);
        }
    }
    */
}