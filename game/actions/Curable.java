package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that should be implemented when an entity is able to be cured.
 *
 */
public interface Curable {
    /**
     * Cures an actor
     * @param actor - Actor to be cured
     * @param map - current game map
     * @param location - location of actor
     * @return - String of result
     */
    String cure(Actor actor, GameMap map, Location location);
}
