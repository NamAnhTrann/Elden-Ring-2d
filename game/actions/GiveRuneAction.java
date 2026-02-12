package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Assistant;

import java.util.Scanner;

public class GiveRuneAction extends Action {

    Assistant assistant;
    Integer amount;
    public GiveRuneAction(Assistant assistant) {
        super();
        this.assistant = assistant;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Adding and Deducting Rune Amount
        amount = actor.getBalance();
        assistant.addBalance(amount);
        actor.deductBalance(amount);
        return actor + " gave " + amount + " runes to " + assistant ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives runes to " + assistant ;
    }
}
