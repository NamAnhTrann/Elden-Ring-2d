package game.items;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * An intrinsic weapon representing a sheep's claw.
 * 
 * Deals 50 damage with the action verb "scratches" and has a 90% chance to hit.
 */
public class SheepClaw extends IntrinsicWeapon {

    /**
     * Constructor for SheepClaw.
     * Sets damage to 50, verb to "scratches", and hit rate to 90%.
     */
    public SheepClaw() {
        super(50, "scratches", 90);
    }
}
