package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.OmenSheep;
import game.behaviours.NormalSelector;

public class OmenSheepEgg extends Egg implements Consumable {

    public OmenSheepEgg() {
        super("OmenSheepEgg", '0', true, 3);
    }

    /***
     * New OmenSheep hatches after 3 turns
     */
    @Override
    public void hatch(Location location) {
        if (getHatchTimer() == 0){
            location.removeItem(this);
            location.addActor(new OmenSheep());
        }
    }

    @Override
    public void consume(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);
        actor.removeItemFromInventory(this);
    }
}
