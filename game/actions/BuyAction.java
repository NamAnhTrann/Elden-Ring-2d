package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Merchant;
import game.actors.Player;
import game.items.WeaponItem;



public class BuyAction extends Action {

    private Item item;
    private int price;
    private Merchant merchant;

    public BuyAction(Item item, int price, Merchant merchant) {
        this.item = item;
        this.price = price;
        this.merchant = merchant;
    }



    @Override
    public String execute(Actor actor, GameMap map) {
        // Checking if the actor has sufficient funds
        if (actor.getBalance() < price) {
            return actor + " does not have enough Runes to buy " + item + ".";
            //logic carried out if there isnt sufficient funds
        }
        //if there is, deduct the balance from the players wallet
        actor.deductBalance(price);

        //add that item to their inventory
        actor.addItemToInventory(item);

        //if the item is an instance of WeaponItem and if the actor is an instance of Player
        //Set that bought weapon to be their equipped weapon
        if (item instanceof WeaponItem weaponItem) {
            if (actor instanceof Player player) {
                player.setEquippedWeapon(weaponItem);
            }
        }

        //allows the merchant to apply the purhcase effects
        merchant.applyPurchaseEffects(actor, item, map);

        return actor + " purchased " + item + " from " + merchant.getName() + " for " + price + " Runes.";


    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " from " + merchant.getName();
    }
}
