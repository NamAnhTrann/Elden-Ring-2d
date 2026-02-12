package game.actors;

import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.behaviours.HealBehaviour;

public class HealingAssistant extends Assistant{

    /**
     * The constructor of the Healing Assistant class.

     */
    public HealingAssistant() {
        super("Healing Assistant", 'C', 50);
        this.addBehaviour(1, new HealBehaviour(this));
        this.addCapability(Ability.CURE_ROT);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(2000));
    }

}
