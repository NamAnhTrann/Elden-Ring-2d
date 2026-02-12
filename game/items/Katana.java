package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Merchant;
import game.actors.OmenSheep;

public class Katana extends WeaponItem{

    public Katana() {
        super("Katana", 'j', 50, "swooshes", 60);
    }

    public void applyPurchaseEffects(Actor buyer, Merchant seller, GameMap map) {
        buyer.hurt(25);
        //since only sellen can sell the katana i used this logic
        if (seller.getName().equals("Sellen")) {
            buyer.heal(10);

            buyer.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20);
            //logic to spawn an omensheep next to the player
            Location sellerLocation = map.locationOf((Actor) seller);

            for (Exit exit: sellerLocation.getExits()) {
                Location nextTo = exit.getDestination();

                if (!nextTo.containsAnActor() && nextTo.getGround().canActorEnter((Actor) seller)) {
                    nextTo.addActor(new OmenSheep());
                    break;

                }

            }
        }

    }

}
