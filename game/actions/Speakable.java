package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;

public interface Speakable {
    List<String> getAvailableMonologues(Actor actor, GameMap map);
}
