package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.SpeakAction;
import game.behaviours.BehaviourSelector;
import game.behaviours.NormalSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract class that represents non-playable characters within the game
 */
public abstract class NonPlayableCharacter extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>(); //THANKS ALEX
    private BehaviourSelector behaviourSelector;

    public NonPlayableCharacter(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviourSelector = new NormalSelector();
    }

    /**
     * The constructor of the NPC class.
     *
     * @param name        the name of the NPC
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the NPCs starting hit points
     */
    public NonPlayableCharacter(String name, char displayChar, int hitPoints, BehaviourSelector newBehaviourSelector) {
        super(name, displayChar, hitPoints);
        this.behaviourSelector = newBehaviourSelector;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for NPC
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return behaviourSelector.selectBehaviour(this, behaviours, map);
    }

    /**
     * The NPC can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !this.hasCapability(Status.PASSIVE_NPC)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * The NPC adds a behaviour given a priority that it has
     * @param priority - priority of behaviour
     * @param behaviour - behaviour to be added
     */
    public void addBehaviour(int priority, Behaviour behaviour){this.behaviours.put(priority, behaviour);}
    //leave this be for ALL npc for this assignment since all "npc" can wandering around map

    public BehaviourSelector getBehaviourSelector() {
        return behaviourSelector;
    }

    public void removeBehaviour(int priority){this.behaviours.remove(priority);};
    public Map<Integer, Behaviour> getBehaviours(){return behaviours;}
}
