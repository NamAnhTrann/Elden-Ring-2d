package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BuyAction;
import game.actions.SpeakAction;
import game.actions.Speakable;
import game.behaviours.NormalSelector;
import game.behaviours.WanderBehaviour;
import game.grounds.GroundStatus;
import game.items.BroadSword;
import game.items.DragonslayerGreatsword;
import java.util.ArrayList;
import java.util.List;

/**
 * Kale is a non-hostile NPC merchant who can be interacted with by the player.
 * 
 * Kale wanders around the map, can speak to the player with context-aware dialogue,
 * and offers items for purchase such as the Broad Sword and Dragonslayer Greatsword.
 * 
 * Kale implements both {@link Speakable} and {@link Merchant} to support interaction
 * and trading functionalities. The player can only access BuyActions if they are marked
 * as {@code HOSTILE_TO_ENEMY}, which is used to signify player control.
 */
public class Kale extends NonPlayableCharacter implements Speakable, Merchant {

    /**
     * Constructor for Kale.
     * Initializes Kale with a name, display character, and 200 HP.
     * Adds {@link Status#PASSIVE_NPC} and {@link WanderBehaviour}.
     */
    public Kale() {
        super("Kale", 'k', 200);
        this.addCapability(Status.PASSIVE_NPC);
        this.addBehaviour(10, new WanderBehaviour());
    }

    /**
     * Determines what actions the given actor can perform on Kale.
     * Adds {@link SpeakAction} if the actor has {@code CAN_SPEAK_TO_NPC}.
     * Adds {@link BuyAction}s if the actor has {@code HOSTILE_TO_ENEMY}.
     *
     * @param otherActor the actor interacting with Kale
     * @param direction  the direction of Kale relative to the actor
     * @param map        the map Kale is currently on
     * @return a list of possible actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.CAN_SPEAK_TO_NPC)) {
            actions.add(new SpeakAction(this));
        }

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (BuyAction action : this.getBuyActions()) {
                actions.add(action);
            }
        }

        return actions;
    }

    /**
     * Returns a list of context-aware monologue lines depending on the state of the player or the map.
     *
     * @param actor the actor speaking to Kale
     * @param map   the map context
     * @return a list of monologue strings
     */
    @Override
    public List<String> getAvailableMonologues(Actor actor, GameMap map) {
        List<String> lines = new ArrayList<>();

        if (actor.getBalance() < 500) {
            lines.add("Ah, hard times, I see. Keep your head low and your blade sharp.");
        }
        if (actor.getItemInventory().isEmpty()) {
            lines.add("Not a scrap to your name? Even a farmer should carry a trinket or two.");
        }
        if (map.locationOf(this).getGround().hasCapability(GroundStatus.CURSE_CURABLE)) {
            lines.add("Rest by the fire while you can, friend. These lands will wear you thin.");
        }

        lines.add("A merchant's life is a lonely one. But the roads... they whisper secrets to those who listen.");

        return lines;
    }

    /**
     * Gets the cost of a specific item sold by Kale.
     *
     * @param item the item being queried
     * @return the price of the item, or {@link Integer#MAX_VALUE} if not for sale
     */
    @Override
    public int getCost(Item item) {
        if (item.toString().equals("Broad Sword")) {
            return 150;
        } else if (item.toString().equals("Dragonslayer Greatsword")) {
            return 1700;
        }

        return Integer.MAX_VALUE;
    }

    /**
     * Applies post-purchase effects for a specific item.
     * Typically handled in the item class (e.g., increasing stats).
     *
     * @param buyer the actor who bought the item
     * @param item  the item being purchased
     * @param map   the game map
     */
    @Override
    public void applyPurchaseEffects(Actor buyer, Item item, GameMap map) {
        if (item instanceof BroadSword broadSword) {
            broadSword.applyPurchaseEffects(buyer, this, map);
        } else if (item instanceof DragonslayerGreatsword dragonslayerGreatsword) {
            dragonslayerGreatsword.applyPurchaseEffects(buyer, this, map);
        }
    }

    /**
     * Gets Kale's name (for use in dialogues or displays).
     *
     * @return the name "Kale"
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a list of {@link BuyAction}s representing items that Kale offers.
     * Used to dynamically generate available buying options.
     *
     * @return a list of BuyActions
     */
    public List<BuyAction> getBuyActions() {
        List<BuyAction> actions = new ArrayList<>();
        actions.add(new BuyAction(new BroadSword(), 150, this));
        actions.add(new BuyAction(new DragonslayerGreatsword(), 1700, this));
        return actions;
    }
}
