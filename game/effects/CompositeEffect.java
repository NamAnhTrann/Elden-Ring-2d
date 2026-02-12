package game.effects;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.List;

/**
 * An effect that combines and applies multiple other effects.
 * 
 * When applied, all contained effects are executed in sequence on the target actor.
 * Useful for bundling together multiple attribute changes or status modifications.
 */
public class CompositeEffect implements Effect {

    private List<Effect> effects;

    /**
     * Constructor.
     *
     * @param newEffects One or more effects to be applied together as a composite.
     */
    public CompositeEffect(Effect... newEffects) {
        this.effects = List.of(newEffects);
    }

    /**
     * Applies all contained effects to the given actor in order.
     *
     * @param actor The actor receiving the effects.
     */
    @Override
    public void apply(Actor actor) {
        for (Effect effect : effects) {
            effect.apply(actor);
        }
    }
}
