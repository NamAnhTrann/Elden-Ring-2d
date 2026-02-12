package game.actors;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * @author Riordan D. Alfredo
 *
 */
public enum Status {
    HOSTILE_TO_ENEMY,
    FOLLOWABLE, // Actor will be followed by those with FollowBehaviour
    PASSIVE_NPC,
    CAN_SPEAK_TO_NPC,
    BUYER,
    HEALABLE,
    BUFFED
}
