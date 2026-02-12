package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BOCAttackAction;
import game.actions.BOCGrowAction;
import game.grow.Growable;
import game.actors.Status;
import game.grow.TreeComponent;

public class BOCBehaviours implements Behaviour {

    private TreeComponent root;
    private Growable growable;

    public BOCBehaviours(TreeComponent root, Growable growable) {
        this.root = root;
        this.growable = growable;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        var here = map.locationOf(actor);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                var dest = here.map().at(here.x() + dx, here.y() + dy);
                if (dest.containsAnActor()) {
                    Actor target = dest.getActor();
                    if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        int totalDamage = 25 + root.getAttackBonus();
                        return new BOCAttackAction(totalDamage, actor, target);
                    }
                }
            }
        }
        return new BOCGrowAction(growable);
    }
}
