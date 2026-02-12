package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class that implements StatusEffect, represents the rot status effect
 */
public class RotStatusEffect extends StatusEffect {
    // Members
    private int lifetime;

    /**
     * Constructor
     * Begins with a certain lifetime for the rotting
     * @param lifetime duration of rotting
     */
    public RotStatusEffect(int lifetime) {
        super("Rot");
        this.lifetime = lifetime;
    }

    @Override
    public void tick(Location location, Actor actor) {
        super.tick(location, actor);
        // Decrease lifetime
        lifetime -= 1;
        if (lifetime <= 0) {
            System.out.println(actor.unconscious(location.map()) + " They died from rot.");
        }
    }
}
