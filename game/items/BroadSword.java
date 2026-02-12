package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Merchant;


public class BroadSword extends WeaponItem{

    public BroadSword() {
        super("Broad Sword", 'b', 30, "slashes", 50);
    }


    //
    public void applyPurchaseEffects(Actor buyer, Merchant seller, GameMap map) {
        //no matter the vendor, by buying this sword the player heals 10 points
        buyer.heal(10);

        if (seller.getName().equals("Kale")) {
            //hardcoding if the seller is kale they will have their base stamina increased by 30
            buyer.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 30);
        } else if (seller.getName().equals("Sellen")) {
            //hardcoding if the seller is sellen then they will have their base health increased by 20
            buyer.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 20);
        }
    }
}
