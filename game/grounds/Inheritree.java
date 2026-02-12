package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that represents inheritree crop
 */
public class Inheritree extends Crop{
    /**
     * Constructor
     */
    public Inheritree(){
        super('t', "Inheritree");
        addCapability(GroundStatus.GOAT_BIRTHABLE);
    }

    /**
     * Manual growing of an inheritree
     * @param location location to be grown at
     * @param actor actor that grows crop
     * @param gameMap current game map
     * @return
     */
    @Override
    public String grow(Location location, Actor actor, GameMap gameMap){
       // Stamina check
        if (!(actor.getAttribute(BaseActorAttributes.STAMINA) >= 25)){return "No stamina to grow. ";}

        // Grow logic
        String result = super.grow(location, actor, gameMap);

        location.getActor().modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,25);

        // Curing adjacent tiles
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(GroundStatus.CURSE_CURABLE)){destination.setGround(new Soil());}
        }

        return result;
    }

    /**
     * Automatic growing of inheritree with no actor present
     * @param location location to be grown at
     * @param gameMap current game map
     * @return
     */
    @Override
    public String grow(Location location, GameMap gameMap){
        String result = super.grow(location, gameMap);

        // Curing adjacent tiles
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(GroundStatus.CURSE_CURABLE)){destination.setGround(new Soil());}
        }
        return result;
    }

    /**
     * Tick of inheritree tile, simulates time movement
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        super.tick(location);

        // Healing and adding stamina to adjacent actors
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if(destination.containsAnActor()){
                Actor actor = destination.getActor();
                actor.heal(5);

                if (actor.getAttribute(BaseActorAttributes.STAMINA) != null){
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 5);
                }
            }
        }
    }
}
