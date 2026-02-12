package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Class representing an action to attack
 * Note that the attacker must have a weapon, e.g.,
 * an intrinsic weapon or a weapon item.
 * Otherwise, the execute method will throw an error.
 *
 * @author Adrian Kristanto, Modified Alexander Wang
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;
    private Location location;

    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target    the actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    public AttackAction(Actor target, Location location, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = weapon.attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        if (direction != null) {
            return actor + " attacks " + target + " at " + direction + " with " + weapon;
        } else if (location != null) {
            return actor + " attacks " + target + " with " + weapon;
        } else {
            return "Invalid attack error. No direction and location";
        }
    }
}


