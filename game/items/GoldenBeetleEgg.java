package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.GoldenBeetle;
import game.behaviours.NormalSelector;
import game.grounds.GroundStatus;

import java.util.List;

/**
 * Concrete class for the golden beetle egg that inherits from egg and interfaces with consumable.
 */
public class GoldenBeetleEgg extends Egg implements Consumable {

    // Constants

    private static final int eggStaminaValue = 1000;
    private static final int eggHatchTime = 99;

    /**
     * Constructor
     */
    public GoldenBeetleEgg() {
        super("GoldenBeetleEgg", '0', true, eggHatchTime);
    }

    /***
     * New GoldenBeetle hatch
     */
    @Override
    public void hatch(Location location) {
        location.removeItem(this);
        location.addActor(new GoldenBeetle());
    }

    @Override
    public void consume(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, eggStaminaValue);
        actor.removeItemFromInventory(this);
    }

    @Override
    public boolean canHatch(Location location){
        if (!location.canActorEnter(new GoldenBeetle())) {
            return false;
        }

        List<Exit> exits = location.getExits();

        for (Exit exit : exits) {
            if (exit.getDestination().getGround().hasCapability(GroundStatus.CURSE_CURABLE)){
                return true;
            }
        }
        return false;

    }
}
