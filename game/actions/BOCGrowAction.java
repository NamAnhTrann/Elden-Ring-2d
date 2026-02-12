package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grow.Growable;

public class BOCGrowAction extends Action {
    private Growable growable;

    public BOCGrowAction(Growable growable) {
        this.growable = growable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println(actor + " is growing...");
        growable.growSelf();
        return"";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows";
    }
}
