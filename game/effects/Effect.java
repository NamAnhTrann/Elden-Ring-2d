package game.effects;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for defining effects that can be applied to actors.
 * 
 * Effects represent any change to an actor's state, such as healing, buffing,
 * restoring attributes, or increasing maximum values.
 * 
 * All implementing classes must define how the effect is applied.
 */
public interface Effect {

    /**
     * Applies the effect to the specified actor.
     *
     * @param actor The actor to which the effect is applied.
     */
    void apply(Actor actor);
}
