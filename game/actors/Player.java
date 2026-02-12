package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.FancyMessage;
import game.items.BareFist;
import game.items.WeaponItem;
import game.time.WorldCycleManager;

/**
 * Class representing the Player.
 * @author Adrian Kristanto
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Status.FOLLOWABLE);
        this.addCapability(Status.CAN_SPEAK_TO_NPC);
        this.addCapability(Status.BUYER);
        this.setIntrinsicWeapon(new BareFist());

        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200)); // Setting stamina to 200
    }

    private WeaponItem equippedWeapon;

    public void setEquippedWeapon(WeaponItem weapon){
        this.equippedWeapon = weapon;
    }

    public WeaponItem getEquippedWeapon() {
        return this.equippedWeapon;
    }



    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        WorldCycleManager.getInstance().tick();
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);

    }

    /**
     * A method for returning the string representation of the player.
     * It displays the player's name and its current hit points + stamina,
     * along with its maximum health hit points + stamina.
     * @return return string
     */
    @Override
    public String toString(){
        // Adding stamina
        return  super.toString() + " HP, (" +
                this.getAttribute(BaseActorAttributes.STAMINA) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.STAMINA) +
                ") ST";
    }

    @Override
    public String unconscious(GameMap gameMap){

        // Adding fancy message
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return super.unconscious(gameMap);
    }

    @Override
    public String unconscious(Actor actor, GameMap map){
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return super.unconscious(map);
    }

}
