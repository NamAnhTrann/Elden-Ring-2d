package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

public class ConsumeAction extends Action{

    Consumable consumable;

    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        consumable.consume(actor, map);
        return consumable + " was consumed by the " + actor;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
