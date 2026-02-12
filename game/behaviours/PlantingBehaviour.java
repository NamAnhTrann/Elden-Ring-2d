package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BirthAction;
import game.actions.PlantAction;
import game.actors.Birthgiver;
import game.actors.PlantingAssistant;

/**
 * Class that checks if a birth giver can give birth
 */
public class PlantingBehaviour implements Behaviour {

    PlantingAssistant planter;

    /**
     * Constructor
     * @param planter The planter
     */
    public PlantingBehaviour(PlantingAssistant planter) {
        this.planter = planter;
    }

    /**
     * Checks if the planter fulfills their condition to plant,
     * and then gives them the action allow them to plant.
     * @param actor the Actor acting (giving birth)
     * @param map the GameMap containing the Actor
     * @return The action of the birth giver giving birth
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (planter.canPlant()){
            System.out.println("Planting behaviour");
            return new PlantAction(planter.getSeed());
        }
        return null;
    }
}
