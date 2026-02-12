package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.NonPlayableCharacter;
import java.util.List;
import java.util.Random;

public class SpeakAction extends Action {

    private Speakable speaker;

    public SpeakAction(Speakable speaker) {
        this.speaker = speaker;
    }

    //actor = player
    //speaker = npc
    @Override
    public String execute(Actor actor, GameMap map) {
        List<String> monologues = speaker.getAvailableMonologues(actor, map);
        if(monologues.isEmpty()){
            return speaker + " has nothing to say.";
        }

        Random random  = new Random();
        String selectedMonologue = monologues.get(random.nextInt(monologues.size()));
        return speaker + " says: " + selectedMonologue;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Listen to: " + speaker;

    }

    

    
    

    
}
