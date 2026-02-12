package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public interface Birthgiver{


    /***
     * Function will carry out the birth-giving
     */
    void giveBirth(Location location);

    boolean canGiveBirth(Location location);
}
