package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * An effect that increases a specific attribute of an actor by a given value.
 * 
 * Uses the INCREASE operation to modify the attribute.
 * Commonly used for healing or stamina restoration.
 */
public class IncreaseAttribute extends AttributeEffector {

    int value;

    /**
     * Constructor.
     *
     * @param newAttribute The attribute to increase (e.g., HEALTH, STAMINA).
     * @param newValue The amount by which to increase the attribute.
     */
    public IncreaseAttribute(BaseActorAttributes newAttribute, int newValue) {
        super(newAttribute);
        this.value = newValue;
    }

    /**
     * Applies the increase effect to the specified actor.
     *
     * @param actor The actor receiving the increased attribute.
     */
    @Override
    public void apply(Actor actor) {
        actor.modifyAttribute(attribute, ActorAttributeOperations.INCREASE, value);
    }
}
