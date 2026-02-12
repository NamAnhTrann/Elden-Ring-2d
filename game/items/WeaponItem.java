package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actors.Status;
import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 *
 * @author Adrian Kristanto
 */
public class WeaponItem extends Item implements Weapon {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private final String verb;
    private float damageMultiplier;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        int finalDamage = Math.round(damage * damageMultiplier);

        if (attacker.hasCapability(Status.BUFFED)) {
            finalDamage = Math.round(finalDamage * 1.5f);
            System.out.println("BUFFED active: boosted damage = " + finalDamage);
        } else {
            System.out.println("Normal attack: damage = " + finalDamage);
        }

        target.hurt(finalDamage);
        return String.format("%s %s %s for %d damage", attacker, verb, target, finalDamage);
    }



    /**
     * List of allowable actions that the item allows its owner do to other actor.
     * Example #1: a weapon can return an attacking action to the other actor.
     * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
     * Example #3: a food can return an action to feed the other actor.
     *
     * @param otherActor the other actor
     * @param location   the location of the other actor
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actionList = new ActionList();
        if (otherActor.hasCapability(Status.PASSIVE_NPC)) {
            actionList.add(new AttackAction(otherActor, location, this));
        }
        return actionList;
    }
}
