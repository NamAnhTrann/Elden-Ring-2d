package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that represents Blood rose crop
 */
public class Bloodrose extends Crop{
    /**
     * Constructor
     */
    public Bloodrose(){
        super('w', "Bloodrose");
    }

    /**
     * Manually grow a bloodrose at a location by an actor
     * @param location location to be grown at
     * @param actor actor that grows crop
     * @param gameMap current game map
     * @return
     */
    @Override
    public String grow(Location location, Actor actor, GameMap gameMap){
        // Stamina check
        if (!(actor.getAttribute(BaseActorAttributes.STAMINA) >= 75)){return "No stamina to grow. ";}

        // Grow logic
        String result = super.grow(location, actor, gameMap);
        actor.hurt(5);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,75);
        if (!actor.isConscious()){System.out.println(actor.unconscious(gameMap));}
        return result;
    }

    /**
     * Tick of bloodrose tile, simulates time movement
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        super.tick(location);

        // Dealing AOE damage
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if(destination.containsAnActor()){
                Actor actor = destination.getActor();
                actor.hurt(10);
                if (!actor.isConscious()){System.out.println(actor.unconscious(location.map()));}
            }
        }

    }
}
