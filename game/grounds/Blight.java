package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.Curable;
import game.actions.CureAction;
import game.actors.Ability;

/**
 * A class representing a blight covering the ground of the valley.
 * @author Adrian Kristanto
 */
public class Blight extends Ground implements Curable {
    public Blight() {
        super('x', "Blight");
        this.addCapability(GroundStatus.CURSE_CURABLE);
    }

    /**
     * Cures blight on ground at a specific location
     * @param actor - Actor that cures
     * @param map - current game map
     * @param location - location of curing
     * @return
     */
    @Override
    public String cure(Actor actor, GameMap map, Location location) {
        // Checking if enough stamina
        if (!(actor.getAttribute(BaseActorAttributes.STAMINA) >= 50)){return "No stamina to cure. ";}

        // Cure logic
        location.setGround(new Soil());
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 50);
        return location + " has been cured to soil. ";
    }
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = super.allowableActions(actor, location, direction);
        if (actor.hasCapability(Ability.CURE_ROT)) {
            actionList.add(new CureAction(this, direction, location));
        }
        return actionList;
    }
}
