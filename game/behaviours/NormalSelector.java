package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.NonPlayableCharacter;

import java.util.Map;

/**
 * A selector that selects the highest priority behaviour to collect the next
 * turn's action from
 */
public class NormalSelector implements BehaviourSelector{

    /**
     * Extracts the action from the highest priority valid behaviour of the npc
     * @param npc The npc with behaviours
     * @param behaviours Map of behaviours and their priorities
     * @param map The GameMap
     * @return The action from the highest priority and valid behaviour. If the whole behaviour list's actions
     * are invalid, return a DoNothingAction
     */
    @Override
    public Action selectBehaviour(NonPlayableCharacter npc, Map<Integer, Behaviour> behaviours, GameMap map) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(npc, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
