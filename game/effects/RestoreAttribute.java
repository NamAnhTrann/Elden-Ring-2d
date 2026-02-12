package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * An effect that fully restores a specific attribute to its maximum value.
 * 
 * Useful for healing or recovering stamina to full.
 */
public class RestoreAttribute extends AttributeEffector {

    /**
     * Constructor.
     *
     * @param newAttribute The attribute to be restored (e.g., HEALTH, STAMINA).
     */
    public RestoreAttribute(BaseActorAttributes newAttribute) {
        super(newAttribute);
    }

    /**
     * Restores the attribute of the given actor to its maximum value.
     *
     * @param actor The actor receiving the restoration.
     */
    @Override
    public void apply(Actor actor) {
        actor.modifyAttribute(attribute, ActorAttributeOperations.UPDATE, actor.getAttributeMaximum(attribute));
    }
}
