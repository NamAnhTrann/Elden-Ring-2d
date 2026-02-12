package game.time;

import java.util.ArrayList;
import java.util.List;

import game.FancyMessage;



/**
 * Manages the game world's time cycle and notifies registered entities when time changes.
 */
public class WorldCycleManager {

    private static WorldCycleManager worldCycleManagerInstance;
    private TimeOfDay currentTime = TimeOfDay.MORNING;
    private int turnCounter = 0;
    private List<TimeSensitiveEntity> subs = new ArrayList<>();

    //note:
    //so for this, this is a Singleton pattern, which basically means that there is only one instance of this class in the game
    //this is useful when we want a global manager, like for this case, it coordinates the world time cycle.
    private WorldCycleManager() {}

    /**
     * Returns the instance of WorldCycleManager.
     *
     * @return the WorldCycleManager instance
     */
    public static WorldCycleManager getInstance() {
        if (worldCycleManagerInstance == null) {
            worldCycleManagerInstance = new WorldCycleManager();
        }
        return worldCycleManagerInstance;
    }

    /**
     * Updates the turn counter and advances time every 2 turns.
     */
    public void tick() {
        turnCounter++;
        if (turnCounter % 2 == 0) {
            moveTimeForward();
        }
    }

    private void moveTimeForward() {
        int nextTimeIndex = (currentTime.ordinal() + 1) % TimeOfDay.values().length;
        currentTime = TimeOfDay.values()[nextTimeIndex];

        if (currentTime == TimeOfDay.MORNING) {
            System.out.println(FancyMessage.MORNING_MSG);
        } else if (currentTime == TimeOfDay.NIGHT) {
            System.out.println(FancyMessage.NIGHT_MSG);
        }

        for (TimeSensitiveEntity entity : subs) {
            entity.onTimeChange(currentTime);
        }
    }

    /**
     * Registers an entity to be notified when the time of day changes.
     *
     * @param entity the entity to register
     */
    public void register(TimeSensitiveEntity entity) {
        subs.add(entity);
    }

    /**
     * Returns the current time of day.
     *
     * @return the current time
     */
    public TimeOfDay getCurrentTime() {
        return currentTime;
    }
}
