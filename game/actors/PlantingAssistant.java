package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.GiveItemAction;
import game.behaviours.PlantingBehaviour;
import game.items.BloodroseSeed;
import game.items.InheritreeSeed;
import game.items.ItemStatus;
import game.items.Seed;

import java.util.List;

public class PlantingAssistant extends Assistant{

    private Integer plantCounter = 0;
    private Integer plantTimer;

    /**
     * The constructor of the Assistant class.

     * @param plantTimer  after how many turns planter will plant
     */
    public PlantingAssistant(int plantTimer) {
        super("Planting Assistant", 'p', 50);
        this.plantTimer = plantTimer;
        this.addBehaviour(0, new PlantingBehaviour(this));
        this.addItemToInventory(new InheritreeSeed());
        this.addItemToInventory(new BloodroseSeed());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        for (Item item: otherActor.getItemInventory()){
            if (item.hasCapability(ItemStatus.PLANTABLE)){
                actions.add(new GiveItemAction(this, item));
            }
        }

        return actions;

    }

    public Boolean canPlant() {
        return ++plantCounter % plantTimer == 0;
    }

    public Seed getSeed() {
        List<Item> seeds = this.getItemInventory();

        if (!seeds.isEmpty()){
            Seed seed = (Seed) seeds.get(0);
            this.removeItemFromInventory(seed);
            return seed;
        }
        return null;
    }
}
