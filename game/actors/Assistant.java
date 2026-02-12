package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CureAction;
import game.actions.Speakable;
import game.behaviours.WanderBehaviour;

import java.util.ArrayList;
import java.util.List;

public abstract class Assistant extends NonPlayableCharacter{
    /**
     * The constructor of the Assistant class.
     *
     * @param name        the name of the NPC
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the NPCs starting hit points
     */
    public Assistant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addBehaviour(10, new WanderBehaviour());
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200));

    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return new ActionList();
    }
//
//    @Override
//    public void addItemToInventory(Item item) {
//        inventory.add(item);
//    }

}
