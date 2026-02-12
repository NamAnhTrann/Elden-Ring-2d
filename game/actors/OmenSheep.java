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
import game.items.InheritreeSeed;
import game.items.OmenSheepEgg;
import game.items.SheepClaw;
import game.time.TimeOfDay;
import game.time.TimeSensitiveEntity;
import game.time.WorldCycleManager;

/**
 * Class that represents omen sheep. Is a NPC, and can be cured
 */
public class OmenSheep extends NonPlayableCharacter implements Curable, Birthgiver, TimeSensitiveEntity {

    private int birthTicker = 0;
    private Behaviour fallback;
    private Behaviour nightAggressive;
    /**
     * The constructor of the OmenSheep Class
     *
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 25);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new BirthBehaviour(this));
        this.addStatusEffect(new RotStatusEffect(15));
        this.addCapability(Status.HEALABLE);

        this.setIntrinsicWeapon(new SheepClaw());
        this.fallback = new WanderBehaviour();
        this.nightAggressive = new NightAggressiveBehaviour(fallback);
        this.addBehaviour(999, fallback);

        WorldCycleManager.getInstance().register(this);
    }

    /**
     * Constructor for OmenSheep given that it has a custom Behaviour Selector
     * to choose its current behaviour
     * @param newBehaviourSelector The custom behaviour selector
     */
    public OmenSheep(BehaviourSelector newBehaviourSelector) {
        super("Omen Sheep", 'm', 25, newBehaviourSelector);
        this.addBehaviour(999, new WanderBehaviour());
        this.addBehaviour(1, new BirthBehaviour(this));
        this.addStatusEffect(new RotStatusEffect(15));
        this.addCapability(Status.HEALABLE);

        this.setIntrinsicWeapon(new SheepClaw());
        this.fallback = new WanderBehaviour();
        this.nightAggressive = new NightAggressiveBehaviour(fallback);
        this.addBehaviour(999, fallback);

        WorldCycleManager.getInstance().register(this);
    }

    /**
     * Cures the omen sheep. In actuality, plants inheritrees around the sheep
     * @param actor - Actor to be cured
     * @param map - current game map
     * @param location - location of actor
     * @return result string
     */
    @Override
    public String cure(Actor actor, GameMap map, Location location) {
        // Finding each adjacent tile that can be planted on
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            if (destination.getGround().hasCapability(GroundStatus.SEED_PLANTABLE) | destination.getGround().hasCapability(GroundStatus.CURSE_CURABLE)){
                InheritreeSeed inheritreeSeed = new InheritreeSeed(); // Planting inheritrees
                destination.setGround(inheritreeSeed.seedCrop());

            }
        }
        return name + " has been cured: planted inheritrees. ";
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Ability.CURE_ROT)) {
            actionList.add(new CureAction(this, direction, map.locationOf(this)));
        }
        return actionList;
    }

    @Override
    public void giveBirth(Location location) {
            location.addItem(new OmenSheepEgg());

    }

    @Override
    public boolean canGiveBirth(Location location) {
        return ++birthTicker % 7 == 0;
    }

    /**
     * This method handles the changes that happen to the OmenSheep
     * when the time period changes
     * @param times The current {@link TimeOfDay} (e.g., DAY or NIGHT).
     */
    @Override
     public void onTimeChange(TimeOfDay times) {
        // If the actor is not conscious, do not change behaviour
        if(!this.isConscious()){
            return;
        }

        if (times == TimeOfDay.NIGHT) {
            this.addBehaviour(0,nightAggressive);
            System.out.println(this + " will now become aggressive because it's night time (testing)");

        } else {
            this.removeBehaviour(0);
            System.out.println(this + " has calmed down as the sun rises.");

        }
    }
}
