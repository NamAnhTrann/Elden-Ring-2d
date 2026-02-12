package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;

import java.util.ArrayList;

/**
 * A teleportation circle ground that allows an actor to teleport to a fixed destination.
 *
 * When an actor stands on this ground, they are given the option to teleport
 * to the specified destination location via a {@link TeleportAction}.
 *
 * Represented on the map with character 'A'.
 */
public class TeleportCircle extends Ground {
    private ArrayList<Location> destinations;

    public TeleportCircle(ArrayList<Location> newDestinations) {
        super('A', "teleport circle");
        destinations = new ArrayList<>();
        destinations.addAll(newDestinations);

    }
    /**
     * Constructor for a teleportation circle that links to a single destination.
     *
     * @param destination The location this teleport circle will send actors to.
     */
    public TeleportCircle(Location destination) {
        super('A', "teleport circle");
        destinations.add(destination);
    }

    /**
     * Returns a list of actions that an actor can perform on this ground.
     *
     * In this case, the actor is offered a teleport action to the set destination.
     *
     * @param actor The actor standing on this ground.
     * @param location The current location of the actor.
     * @param direction The direction of the ground relative to the actor.
     * @return A list of possible actions (e.g., teleporting).
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for (Location destination : destinations){
            actions.add(new TeleportAction(destination));
        }

        return actions;
    }
}
