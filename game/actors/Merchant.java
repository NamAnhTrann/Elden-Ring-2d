package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

public interface Merchant {

    /**
     * Initialises the functions to be used by Kale and Sellen
     * GetCost to get the different cost of different swords
     * ApplyPurchaseEffects to apply the different effects of the sword sold by different merchant
     * GetName to get the name of the merchant
     */
    int getCost(Item item);

    void applyPurchaseEffects(Actor buyer, Item item, GameMap map);

    String getName();

}
