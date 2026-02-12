package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Behaviour that allows an actor to attack a nearby actor if one is adjacent.
 * If no valid targets are found, the fallback behaviour is executed.
 *
 * Used to simulate aggressive enemies that randomly attack nearby actors when the condition (e.g., night) applies.
 */
public class NightAggressiveBehaviour implements Behaviour {
    private Behaviour fallback;
    private Random random = new Random();

    /**
     * Constructor.
     * 
     * @param fallback a fallback Behaviour to execute when there are no valid targets to attack
     */
    public NightAggressiveBehaviour(Behaviour fallback) {
        this.fallback = fallback;
    }

    /**
     * Searches surrounding locations for attackable actors and randomly chooses one to attack.
     * If no actors are found, the fallback behaviour is used instead.
     *
     * @param actor the actor with this behaviour
     * @param map the map containing the actor
     * @return an AttackAction if a target is found, otherwise the fallback Behaviour's action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Action> attackOptions = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if (!target.equals(actor)) {
                    attackOptions.add(new AttackAction(target, exit.getName()));
                }
            }
        }
        if (!attackOptions.isEmpty()) {
            System.out.println("NightAggressiveBehaviour: " + actor + " is attacking! (for testing)");
            return attackOptions.get(random.nextInt(attackOptions.size()));
        }
        return fallback.getAction(actor, map);
    }
}
