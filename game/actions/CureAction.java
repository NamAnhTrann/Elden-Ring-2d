package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing an action to heal
 */
public class CureAction extends Action {

    /**
     * The target that is to be cured
     */
    private Curable target;

    /**
     * The direction of incoming cure.
     */
    private String direction;

    /**
     * The location of cure
     */
    private Location location;
    /**
     * Constructor.
     *
     * @param target the Actor to cure
     * @param direction the direction where heal should be performed (only used for display purposes)
     */
    public CureAction(Curable target, String direction, Location location) {
        this.target = target;
        this.direction = direction;
        this.location = location;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        String result = target.cure(actor, map, location);
        result += "This was done by " + actor;
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " cures " + target + " " + direction;
    }
}

