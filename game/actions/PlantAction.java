package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.GroundStatus;
import game.items.Seed;

/**
 * Class that represents a plant action
 */
public class PlantAction extends Action {

    /**
     * Seed used for planting
     */
    private Seed seed;

    /**
     * Constructor.
     * @param seed seed used to be planted
     **/
    public PlantAction(Seed seed) {
        this.seed = seed;
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        if (this.seed == null) {
            return actor + "failed to plant";
        }
        String result = seed.plant(map.locationOf(actor), actor,map);

        // If seed was planted, then ground is no longer soil
        if (!map.locationOf(actor).getGround().hasCapability(GroundStatus.SEED_PLANTABLE)) {actor.removeItemFromInventory(seed);}

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return (actor + " plants " + seed);
    }
}
