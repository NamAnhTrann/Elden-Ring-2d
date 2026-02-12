package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actors.Ability;

/**
 * A class representing a Medicine that an actor can pick up and drop
 * @author Adrian Kristanto
 */
public class Medicine extends Item{
    public Medicine() {
        super("Medicine", 'o', true);
        this.addCapability(Ability.CURE_ROT); // Ability to cure rot
    }
}
