package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.NonPlayableCharacter;

import java.util.Map;

/**
 * Interface that handles the selection of a behaviour within a list of behaviours for a
 * NPC
 */
public interface BehaviourSelector {
    Action selectBehaviour(NonPlayableCharacter npc, Map<Integer, Behaviour> behaviours, GameMap map);

}
