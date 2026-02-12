package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An action that teleports an actor to a predefined location.
 * 
 * Typically used with {@link game.grounds.TeleportCircle} to allow map traversal via teleportation.
 */
public class TeleportAction extends Action {
    private Location destination;

    /**
     * Constructor.
     *
     * @param destination The target location to teleport the actor to.
     */
    public TeleportAction(Location destination) {
        this.destination = destination;
    }

    /**
     * Executes the teleportation by removing the actor from the current map
     * and placing them at the destination location.
     *
     * @param actor The actor performing the teleportation.
     * @param map The map the actor is currently on.
     * @return A description of the action result.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        destination.map().addActor(actor, destination);
        return actor + " teleported to " + destination;
    }

    /**
     * Provides a description for the teleport action to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the teleport option.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + destination;
    }
}
