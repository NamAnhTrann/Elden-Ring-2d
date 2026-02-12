package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Assistant;
import game.actors.PlantingAssistant;
import game.items.Seed;

public class GiveItemAction extends Action {

    Assistant assistant;
    Item item;

    public GiveItemAction(Assistant assistant, Item item) {
        super();
        this.assistant = assistant;
        this.item = item;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        assistant.addItemToInventory(item);
        actor.removeItemFromInventory(item);
        return actor + " gave " + item + " to " + assistant ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + item + " to " + assistant ;
    }
}
