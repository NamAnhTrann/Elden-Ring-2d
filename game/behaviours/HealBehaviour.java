package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.BirthAction;
import game.actions.Curable;
import game.actions.CureAction;
import game.actors.Ability;
import game.actors.Birthgiver;
import game.actors.HealingAssistant;
import game.actors.Status;
import game.grounds.GroundStatus;

/**
 * Class that checks if a birth giver can give birth
 */
public class HealBehaviour implements Behaviour {

    HealingAssistant healingAssistant;

    /**
     * Constructor
     * @param healingAssistant The birth giver to be checked
     */
    public HealBehaviour(HealingAssistant healingAssistant) {
        this.healingAssistant = healingAssistant;
    }

    /**
     * Checks if possible to heal
     * @param actor the Actor acting (healing)
     * @param map the GameMap containing the Actor
     * @return The action of the healer
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if (target.hasCapability(Status.HEALABLE)){
                    return (new CureAction((Curable) target, exit.getHotKey(), destination));
                }
            }
            if (destination.getGround().hasCapability(GroundStatus.CURSE_CURABLE)){
                return (new CureAction((Curable) destination.getGround(), exit.getHotKey(), destination));
            }
        }
        return null;
    }
}
