package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * An effect that increases the maximum value of a specific actor attribute.
 *
 * Commonly used to boost an actor’s potential in areas like health or stamina beyond the default cap.
 */
public class IncreaseAttributeMaximum extends AttributeEffector {

    int value;

    /**
     * Constructor.
     *
     * @param newAttribute The attribute whose maximum value is to be increased.
     * @param newValue The amount to increase the maximum value by.
     */
    public IncreaseAttributeMaximum(BaseActorAttributes newAttribute, int newValue) {
        super(newAttribute);
        this.value = newValue;
    }

    /**
     * Applies the maximum increase to the specified actor's attribute.
     *
     * @param actor The actor receiving the increased attribute maximum.
     */
    @Override
    public void apply(Actor actor) {
        actor.modifyAttributeMaximum(attribute, ActorAttributeOperations.INCREASE, value);
    }
}
