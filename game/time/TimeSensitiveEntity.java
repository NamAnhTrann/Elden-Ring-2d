package game.time;


/**
 * An interface for entities that respond to changes in time (e.g., day or night).
 * 
 * Implementing classes must define behaviour that should occur when the time of day changes.
 * This supports dynamic interactions and state changes based on the in-game time cycle.
 */
public interface TimeSensitiveEntity {
    
    /**
     * Called when the time of day changes.
     *
     * @param times The current {@link TimeOfDay} (e.g., DAY or NIGHT).
     */
    void onTimeChange(TimeOfDay times);
}
