package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.GoldenBeetle;
import game.actors.Merchant;

public class DragonslayerGreatsword extends WeaponItem{

    public DragonslayerGreatsword() {
        super("Dragonslayer Greatsword", 'D', 70, "slices", 75);
    }

    //hardcoding the conditions
    public void applyPurchaseEffects(Actor buyer, Merchant seller, GameMap map) {

        buyer.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);

        if (seller.getName().equals("Kale")) {
            buyer.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 30);
        } else if (seller.getName().equals("Sellen")) {
            //logic to spawn the golden beetle next to the player
            Location buyerLocation = map.locationOf(buyer);

            for (Exit exit: buyerLocation.getExits()) {
                Location nextTo = exit.getDestination();

                if (!nextTo.containsAnActor() && nextTo.getGround().canActorEnter(buyer)) {
                    nextTo.addActor(new GoldenBeetle());
                    break;
                }

            }

        }

    }

}
