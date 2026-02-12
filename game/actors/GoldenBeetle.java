package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.behaviours.*;
import game.grounds.GroundStatus;
import game.items.Consumable;
import game.items.GoldenBeetleEgg;

import java.util.List;


/**
 * Class that represents Golden Beetle. Is a NPC, and can give birth
 */
public class GoldenBeetle extends NonPlayableCharacter implements Birthgiver, Consumable {

    // Member Variables
    private int birthTicker = 0;
    private static final int birthCountdown = 5;
    private static final int runeValue = 1000;
    private static final int healValue = 15;

    // Behaviour Keys
    private static final int followKey = 2;

    /**
     * The constructor of the GoldenBeetle Class
     *
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new BirthBehaviour(this));
    }

    public GoldenBeetle(BehaviourSelector newBehaviourSelector) {
        super("Golden Beetle", 'b', 25, newBehaviourSelector);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new BirthBehaviour(this));
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        actionList.add(new ConsumeAction(this));
        return actionList;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Check to see if followed target still exists
        if (this.getBehaviours().containsKey(followKey)){
            Action action = this.getBehaviours().get(followKey).getAction(this, map);
            if (!(action == null)){return super.playTurn(actions, lastAction, map, display);}
            this.removeBehaviour(followKey);
        }

        // If does not have follow target
        List<Exit> exits = map.locationOf(this).getExits();
        for (Exit exit : exits) {
            if (exit.getDestination().containsAnActor()){
                Actor adjacentActor = exit.getDestination().getActor();
                if (adjacentActor.hasCapability(Status.FOLLOWABLE)) {
                    this.addBehaviour(followKey, new FollowBehaviour(adjacentActor));
                }
            }
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public void giveBirth(Location location) {
        location.addItem(new GoldenBeetleEgg());

    }

    @Override
    public boolean canGiveBirth(Location location) {
        return ++birthTicker % birthCountdown == 0;
    }

    @Override
    public void consume(Actor actor, GameMap map)
    {
        actor.addBalance(runeValue);
        actor.heal(healValue);
        map.removeActor(this);
    };

}
