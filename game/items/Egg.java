package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public abstract class Egg extends Item {

    private int hatchTimer;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Egg(String name, char displayChar, boolean portable, int hatchTimer) {
        super(name, displayChar, portable);
        this.hatchTimer = hatchTimer;
    }

    /***
     * Abstract function to define the hatching mechanic for the egg
     * @param location location of the egg on the GameMap
     */
    public abstract void hatch(Location location);

    /***
     * @return current state of the hatchTimer
     */
    public int getHatchTimer() {
        return hatchTimer;
    }

    /***
     * Function to decrement hatchTimer (countdown)
     */
    private void countdownHatchTimer() {
        hatchTimer--;
    }

    /***
     * Function that returns boolean of whether the egg can be hatched
     * @param location The location of the ground on which we lie.
     */
    public boolean canHatch(Location location){return getHatchTimer() == 0;}

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        countdownHatchTimer();
        if (canHatch(currentLocation)){
            hatch(currentLocation);
        }
    }
}
