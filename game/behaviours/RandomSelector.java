package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.NonPlayableCharacter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * A selector that selects a random behaviour to collect the next
 * turn's action from
 */
public class RandomSelector implements BehaviourSelector{

    /**
     * Extracts the action from a random behaviour in the npc's behaviourlist
     * @param npc The npc with behaviours
     * @param behaviours Map of behaviours and their priorities
     * @param map The GameMap
     * @return The action from the a random behaviour. If random behaviour's action is invalid, return
     * DoNothingAction.
     */
    @Override
    public Action selectBehaviour(NonPlayableCharacter npc, Map<Integer, Behaviour> behaviours, GameMap map) {
        ArrayList<Behaviour> behaviourList = new ArrayList<>(behaviours.values());

        if(!behaviourList.isEmpty()) {
            Random rand = new Random();
            Behaviour behaviour = behaviourList.get(rand.nextInt(behaviourList.size()));

            Action action = behaviour.getAction(npc, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
    }
}
