package game.actors;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.Curable;
import game.behaviours.BehaviourSelector;
import game.actions.CureAction;
import game.behaviours.BirthBehaviour;
import game.behaviours.NormalSelector;
import game.behaviours.NightAggressiveBehaviour;
import game.behaviours.WanderBehaviour;
import game.grounds.GroundStatus;
import game.items.GoatHorn;
import game.time.TimeOfDay;
import game.time.TimeSensitiveEntity;
import game.time.WorldCycleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that represents Spirit Goat NPC that is also curable
 */
public class SpiritGoat extends NonPlayableCharacter implements Curable, Birthgiver, TimeSensitiveEntity {

    // Members
    private RotStatusEffect rotStatus = new RotStatusEffect(10);
    private Behaviour fallback;
    private Behaviour nightAggressive;

    /**
     * The constructor of the SpiritGoat Class
     *
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new BirthBehaviour(this));
        this.addStatusEffect(rotStatus); // Rotting at beginning of life
        this.addCapability(Status.HEALABLE);

        //A3 stuff fallbacks
        this.setIntrinsicWeapon(new GoatHorn());
        this.fallback = new WanderBehaviour();
        this.nightAggressive = new NightAggressiveBehaviour(fallback);
        this.addBehaviour(999, fallback);
        WorldCycleManager.getInstance().register(this);
    }

    /**
     * Constructor for SpiritGoat given that it has a custom Behaviour Selector
     * to choose its current behaviour
     * @param newBehaviourSelector The custom behaviour selector
     */
    public SpiritGoat(BehaviourSelector newBehaviourSelector) {
        super("Spirit Goat", 'y', 50, newBehaviourSelector);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new BirthBehaviour(this));
        this.addStatusEffect(rotStatus); // Rotting at beginning of life
        this.addCapability(Status.HEALABLE);

        //A3 stuff fallbacks
        this.setIntrinsicWeapon(new GoatHorn());
        this.fallback = new WanderBehaviour();
        this.nightAggressive = new NightAggressiveBehaviour(fallback);
        this.addBehaviour(999, fallback);
        WorldCycleManager.getInstance().register(this);

    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Ability.CURE_ROT)) {
            actionList.add(new CureAction(this, direction, map.locationOf(this)));
        }
        return actionList;
    }

    /**
     * Cures the spirit goat, resets rot timer
     * @param actor - Actor to be cured
     * @param map - current game map
     * @param location - location of actor
     * @return
     */
    @Override
    public String cure(Actor actor, GameMap map, Location location) {
        this.removeStatusEffect(rotStatus);
        rotStatus = new RotStatusEffect(10);
        this.addStatusEffect(rotStatus);
        return name + " has reset its rotting. ";
    }

    @Override
    public void giveBirth(Location location) {
        List<Exit> exits = location.getExits();
        List<Exit> possibleExits = new ArrayList<Exit>();
        for (Exit exit : exits) {
            if (exit.getDestination().canActorEnter(this)) {
                possibleExits.add(exit);
            };
        }

        if (!possibleExits.isEmpty()) {
            Random rand = new Random();
            possibleExits.get(rand.nextInt(possibleExits.size())).getDestination().addActor(new SpiritGoat());
        }

        Random rand = new Random();
        // Adrian (Random Behaviours): New goats will have same behaviour as their parent
        possibleExits.get(rand.nextInt(possibleExits.size())).getDestination().addActor(new SpiritGoat(getBehaviourSelector()));
    }

    @Override
    public boolean canGiveBirth(Location location) {
        List<Exit> exits = location.getExits();

        for (Exit exit : exits) {
            if (exit.getDestination().getGround().hasCapability(GroundStatus.GOAT_BIRTHABLE)){
                return true;
            }
    }
        return false;
    }

    /**
     * This method handles the changes that happen to the SpiritGoat
     * when the time period changes
     * @param times The current {@link TimeOfDay} (e.g., DAY or NIGHT).
     */
    @Override
    public void onTimeChange(TimeOfDay times) {
        if(!this.isConscious()){
            return;
        }
        if (times == TimeOfDay.NIGHT) {
            this.addBehaviour(0, nightAggressive);
            System.out.println(this + " will now become aggressive because it's night time (testing)");  // enable aggression condition
        } else {
            this.removeBehaviour(0);
            System.out.println(this + " has calmed down as the sun rises.");
        }
    }
}
