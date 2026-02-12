package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PlantAction;
import game.grounds.Crop;
import game.grounds.GroundStatus;

/**
 * An abstract class that should be used to represent a seed item that is also planatable
 */
public abstract class Seed extends Item implements Plantable{


    /***
     * Constructor.
     *  @param name the name of this Seed
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Seed(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        addCapability(ItemStatus.PLANTABLE);
    }

    public String plant(Location location, Actor actor, GameMap gameMap){
        Crop crop = seedCrop();
        String result = crop.grow(location, actor, gameMap);
        return result;
    };

    public String plant(Location location, GameMap gameMap){
        Crop crop = seedCrop();
        crop.grow(location, gameMap);
        return this + " has been planted at " + location + " to create a " + crop;
    };

    /**
     * Returns the new crop
     * @return Crop - grown crop
     */
    abstract Crop seedCrop();

    /**
     * Boolean expression to find whether the location is plantable
     * @param location - location to be planted on
     * @param actor - actor who plants
     * @param map current game map
     * @return result string
     */
    public boolean canPlant(Location location, Actor actor, GameMap map){
        return (location.getGround().hasCapability(GroundStatus.SEED_PLANTABLE));
    }

    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = super.allowableActions(actor, map);

        if (canPlant(map.locationOf(actor), actor, map)){
            Action plantAction = new PlantAction(this);
            actions.add(plantAction);
        }
        return actions;
    }
}
