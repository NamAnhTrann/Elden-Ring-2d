package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that simulates how a hostile NPC will act with
 * attack-able entities around them
 */
public class AttackBehaviour implements Behaviour {
    private final Random random = new Random();

    /**
     * Finds all attack-able entities around the attacking actor and randomly
     * chooses one with more than 50 health to attack.
     *
     * @param actor the Actor acting (attacking)
     * @param map the GameMap containing the Actor
     * @return An AttackAction to a random attack-able entity around the actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> attackActionsBehaviour = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor target = destination.getActor();

                if (!target.equals(actor) &&
                    target.getAttribute(BaseActorAttributes.HEALTH) > 50) {
                    attackActionsBehaviour.add(new AttackAction(target, exit.getName()));
                }
            }
        }
        if (!attackActionsBehaviour.isEmpty()) {
            return attackActionsBehaviour.get(random.nextInt(attackActionsBehaviour.size()));
        }
        return null;
    }


}
