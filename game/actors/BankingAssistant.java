package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.GiveRuneAction;
import game.actions.TakeRuneAction;

import static java.lang.Math.floor;

public class BankingAssistant extends Assistant{

    private double interest;

    /**
     * The constructor of the Banking Assistant class.

     * @param interest  how much interest is accumulated per round
     */
    public BankingAssistant(double interest) {
        super("Banking Assistant", 'B', 50);
        this.interest = interest;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        actions.add(new GiveRuneAction(this));
        actions.add(new TakeRuneAction(this));

        return actions;

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        int amount = getBalance();
        if (amount != 0) {
            addBalance((int) floor(amount * interest));
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String toString() {
        String result = super.toString();
        return result + ", Balance: (" + getBalance() + ")";
    }
}
