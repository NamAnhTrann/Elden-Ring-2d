package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Status;
import game.effects.*;
import game.time.TimeOfDay;
import game.time.WorldCycleManager;

/**
 * An action that allows an actor to rest at a campfire.
 *
 * The outcome of resting depends on the current time of day:
 * - In the morning: Resting restores a small amount of health if the actor's health is below 50.
 * - At night: Resting grants buffs by increasing the actor’s maximum health and stamina,
 *   and fully restores both attributes.
 *
 * The action uses a composite effect to apply multiple status boosts at night, and
 * ensures that buffs are only applied once per night using the BUFFED status.
 */
public class RestAction extends Action {

    private final Effect dayRestEffect;
    private final Effect nightRestEffects;

    /**
     * Constructor. Sets up day and night rest effects.
     */
    public RestAction() {
        dayRestEffect = new IncreaseAttribute(BaseActorAttributes.HEALTH, 25);
        nightRestEffects = new CompositeEffect(
                new IncreaseAttributeMaximum(BaseActorAttributes.HEALTH, 40),
                new IncreaseAttributeMaximum(BaseActorAttributes.STAMINA, 40),
                new RestoreAttribute(BaseActorAttributes.HEALTH),
                new RestoreAttribute(BaseActorAttributes.STAMINA)
        );
    }

    /**
     * Executes the rest action, applying effects based on the current time.
     *
     * @param actor The actor performing the rest.
     * @param map The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        TimeOfDay currentTime = WorldCycleManager.getInstance().getCurrentTime();

        if (currentTime == TimeOfDay.MORNING) {
            int currentHealth = actor.getAttribute(BaseActorAttributes.HEALTH);
            if (currentHealth < 50) {
                dayRestEffect.apply(actor);
                return actor + " rests and recovers some health.";
            } else {
                return actor + " is already too healthy to benefit from resting.";
            }
        } else if (currentTime == TimeOfDay.NIGHT) {
            if (!actor.hasCapability(Status.BUFFED)) {
                nightRestEffects.apply(actor);
                actor.addCapability(Status.BUFFED);
            }
            return actor + " rests at night and is fully restored with bonus strength.";
        }

        return actor + " tries to rest, but nothing happens.";
    }

    /**
     * Returns the description of this action for the game menu.
     *
     * @param actor The actor performing the action.
     * @return The string to display in the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at the campfire";
    }
}
