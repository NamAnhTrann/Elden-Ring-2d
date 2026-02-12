package game.items;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * An intrinsic weapon representing a goat's horn.
 * 
 * Deals 30 damage with the action verb "headbutts" and has a 75% chance to hit.
 */
public class GoatHorn extends IntrinsicWeapon {

    /**
     * Constructor for GoatHorn.
     * Sets damage to 30, verb to "headbutts", and hit rate to 75%.
     */
    public GoatHorn() {
        super(30, "headbutts", 75);
    }
}
