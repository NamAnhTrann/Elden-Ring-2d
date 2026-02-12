package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Assistant;

import java.util.Scanner;

public class TakeRuneAction extends Action {

    Assistant assistant;
    Integer amount;
    public TakeRuneAction(Assistant assistant) {
        super();
        this.assistant = assistant;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Adding and Deducting Rune Amount
        int amount = assistant.getBalance();
        assistant.deductBalance(amount);
        actor.addBalance(amount);
        return actor + " takes " + amount + " runes from " + assistant ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " takes runes from " + assistant ;
    }
}
