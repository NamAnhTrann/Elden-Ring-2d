package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Birthgiver;

/**
 * Class representing an action of giving birth
 */
public class BirthAction extends Action {

    /**
     * The actor that is to give birth
     */
    Birthgiver birthgiver;

    public BirthAction(Birthgiver birthgiver) {
        this.birthgiver = birthgiver;
    }

    /**
     * The birth giver executes their specific method of giving birth
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        birthgiver.giveBirth(map.locationOf(actor));
        return menuDescription(actor);
    }

    /**
     * The post-completion message of the action
     * @param actor The actor performing the action.
     * @return A string indicating which actor just gave birth
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has given birth";
    }
}
