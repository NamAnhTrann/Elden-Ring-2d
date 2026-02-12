package game.effects;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;

/**
 * An abstract class for effects that modify a specific base actor attribute.
 * 
 * Serves as a common parent for all effects that target a single attribute, such as health or stamina.
 * 
 * Subclasses should implement how the attribute is affected (e.g., increased, restored).
 */
public abstract class AttributeEffector implements Effect {

    BaseActorAttributes attribute;

    /**
     * Constructor.
     *
     * @param newAttribute The attribute that this effect will modify.
     */
    public AttributeEffector(BaseActorAttributes newAttribute) {
        this.attribute = newAttribute;
    }
}
