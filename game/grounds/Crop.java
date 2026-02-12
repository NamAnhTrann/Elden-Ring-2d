package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An abstract class that should be implemented by crops, is an extension of ground tile
 */
public abstract class Crop extends Ground {
    /**
     * Constructor
     *
     * @param displayChar display symbol
     * @param name name of crop
     */
    public Crop(char displayChar, String name){
        super(displayChar, name);
    }

    /**
     * Used for manually growing by an actor at a location
     * @param location location to be grown at
     * @param actor actor that grows crop
     * @param gameMap current game map
     * @return result string
     */
    public String grow(Location location, Actor actor, GameMap gameMap){
        location.setGround(this);
        return this + " has been grown at " + location + " by " + actor;
    }

    /**
     * Used for growing where an actor is not present
     * @param location location to be grown at
     * @param gameMap current game map
     * @return result string
     */
    public String grow(Location location, GameMap gameMap){
        location.setGround(this);
        return this + " has been grown at " + location;
    }
}
