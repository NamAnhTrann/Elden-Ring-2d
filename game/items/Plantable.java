package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that should be implemented by classes that can be planted
 */
public interface Plantable {
    /**
     *  Plants at a current location by an actor
     *
     * @param location - Location to be planted
     * @param actor - Actor to plant
     * @param map - Current game map
     * @return - String of Result
     */
    String plant(Location location, Actor actor, GameMap map);

    boolean canPlant(Location location, Actor actor, GameMap map);
}
