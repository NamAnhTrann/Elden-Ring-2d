package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Consumable {

    public void consume(Actor actor, GameMap map);

}
