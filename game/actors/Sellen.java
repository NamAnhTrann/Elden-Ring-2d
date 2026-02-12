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
import game.items.BroadSword;
import game.items.DragonslayerGreatsword;
import game.items.Katana;
import java.util.ArrayList;
import java.util.List;

/**
 * Sellen is a passive NPC merchant who wanders the map and can interact with the player.
 * <p>
 * Sellen offers lore-rich dialogue through {@link SpeakAction} and can sell various weapons.
 * Implements {@link Speakable} and {@link Merchant} to support both interactions and purchases.
 * The items sold and their prices are hardcoded in {@link #getBuyActions()} and {@link #getCost(Item)}.
 * </p>
 */
public class Sellen extends NonPlayableCharacter implements Speakable, Merchant {

    /**
     * Constructs a new Sellen NPC with name, display character, and HP.
     * Adds {@link Status#PASSIVE_NPC} and a {@link WanderBehaviour}.
     */
    public Sellen() {
        super("Sellen", 's', 150);
        this.addCapability(Status.PASSIVE_NPC);
        this.addBehaviour(10, new WanderBehaviour());
    }

    /**
     * Determines the actions another actor can perform on Sellen.
     * Includes speaking and buying items if the actor has appropriate capabilities.
     *
     * @param otherActor the actor interacting with Sellen
     * @param direction  the direction of Sellen relative to the actor
     * @param map        the game map
     * @return list of allowable actions, including {@link SpeakAction} and {@link BuyAction}s
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
     * Returns a list of monologue lines for Sellen.
     * These provide backstory and atmospheric flavor.
     *
     * @param actor the actor Sellen is speaking to
     * @param map   the game map
     * @return list of monologue strings
     */
    @Override
    public List<String> getAvailableMonologues(Actor actor, GameMap map) {
        List<String> lines = new ArrayList<>();
        lines.add("The academy casts out those it fears. Yet knowledge, like the stars, cannot be bound forever.");
        lines.add("You sense it too, don’t you? The Glintstone hums, even now.");
        return lines;
    }

    /**
     * Returns the cost of an item sold by Sellen.
     *
     * @param item the item being queried
     * @return the item's cost or {@link Integer#MAX_VALUE} if not for sale
     */
    @Override
    public int getCost(Item item) {
        if (item.toString().equals("Broad Sword")) {
            return 100;
        } else if (item.toString().equals("Dragonslayer Greatsword")) {
            return 1500;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Applies effects when the player purchases an item from Sellen.
     * Delegates behavior to the item's `applyPurchaseEffects` method.
     *
     * @param buyer the actor who made the purchase
     * @param item  the item purchased
     * @param map   the game map
     */
    @Override
    public void applyPurchaseEffects(Actor buyer, Item item, GameMap map) {
        if (item instanceof BroadSword broadSword) {
            broadSword.applyPurchaseEffects(buyer, this, map);
        } else if (item instanceof DragonslayerGreatsword dragonslayerGreatsword) {
            dragonslayerGreatsword.applyPurchaseEffects(buyer, this, map);
        } else if (item instanceof Katana katana) {
            katana.applyPurchaseEffects(buyer, this, map);
        }
    }

    /**
     * Gets Sellen’s name.
     *
     * @return the name "Sellen"
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the list of {@link BuyAction}s for items that Sellen sells.
     *
     * @return list of buy actions available from Sellen
     */
    public List<BuyAction> getBuyActions() {
        List<BuyAction> actions = new ArrayList<>();
        actions.add(new BuyAction(new BroadSword(), 100, this));
        actions.add(new BuyAction(new DragonslayerGreatsword(), 1500, this));
        actions.add(new BuyAction(new Katana(), 500, this));
        return actions;
    }
}
