package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.actions.Speakable;
import game.behaviours.AttackBehaviour;
import game.behaviours.NormalSelector;
import game.behaviours.WanderBehaviour;
import game.items.BareFist;
import java.util.ArrayList;
import java.util.List;

/**
 * Guts is a powerful non-playable character (npc) who is hostile and can be spoken to.
 * 
 * Guts has high health, wanders the map, attacks nearby actors if their health is above 50,
 * and says aggressive monologue lines when interacted with by the player.
 * 
 * Guts is equipped with an intrinsic weapon (BareFist) and is capable of both wandering
 * and engaging in combat.
 * 
 * Implements {@link Speakable} to allow monologue interactions via {@link SpeakAction}.
 */
public class Guts extends NonPlayableCharacter implements Speakable {

    /**
     * Constructs a new Guts npc with 500 HP, the ability to attack, and an aggressive personality.
     * Adds both {@link WanderBehaviour} and {@link AttackBehaviour} to its behaviour map.
     */
    public Guts() {
        super("Guts", 'g', 500);
        this.addCapability(Status.HOSTILE_TO_ENEMY);  // Can be attacked
        this.setIntrinsicWeapon(new BareFist());
        this.addBehaviour(10, new WanderBehaviour());
        this.addBehaviour(1, new AttackBehaviour());
    }

    /**
     * Determines what actions another actor can perform on Guts.
     * Allows talking if the actor has the {@code CAN_SPEAK_TO_NPC} status.
     *
     * @param otherActor the actor interacting with Guts
     * @param direction the direction of Guts relative to the actor
     * @param map the map Guts is currently on
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.CAN_SPEAK_TO_NPC)) {
            actions.add(new SpeakAction(this));
        }
        return actions;
    }

    /**
     * Returns the list of monologue lines Guts can say based on the state of the interacting actor.
     * If the actor's health is below 50, a taunting line is added.
     *
     * @param actor the actor listening to the monologue
     * @param map the game map context
     * @return a list of monologue strings
     */
    @Override
    public List<String> getAvailableMonologues(Actor actor, GameMap map) {
        List<String> lines = new ArrayList<>();

        if (actor.getAttribute(BaseActorAttributes.HEALTH) < 50) {
            lines.add("WEAK! TOO WEAK TO FIGHT ME!");
        }

        lines.add("RAAAAGH!");
        lines.add("I’LL CRUSH YOU ALL!");

        return lines;
    }
}
