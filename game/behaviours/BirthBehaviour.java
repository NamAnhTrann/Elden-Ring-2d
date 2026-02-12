package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BirthAction;
import game.actors.Birthgiver;

/**
 * Class that checks if a birth giver can give birth
 */
public class BirthBehaviour implements Behaviour {

    Birthgiver birthgiver;

    /**
     * Constructor
     * @param birthgiver The birth giver to be checked
     */
    public BirthBehaviour(Birthgiver birthgiver) {
        this.birthgiver = birthgiver;
    }

    /**
     * Checks if the birth giver fulfills their condition to give birth,
     * and then gives them the action allow them to give birth.
     * @param actor the Actor acting (giving birth)
     * @param map the GameMap containing the Actor
     * @return The action of the birth giver giving birth
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (birthgiver.canGiveBirth(map.locationOf(actor))){
            return new BirthAction(birthgiver);
        }
        return null;
    }
}
