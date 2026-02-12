package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.NonPlayableCharacter;

public class BOCAttackAction extends Action {

    private int damage;
    private Actor boss;
    private Actor target;

    public BOCAttackAction(int damage, Actor boss, Actor target) {
        this.damage = damage;
        this.boss = boss;
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        target.hurt(damage);

        String result = boss + " smacks " + target + " for " + damage + " damage";

        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return boss + " smacks " + target + " for " + damage + " damage";
    }
}
