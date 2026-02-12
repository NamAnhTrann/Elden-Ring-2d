package game.items;

import game.grounds.Crop;
import game.grounds.Inheritree;
/**
 * Class that represents Blood rose seed class
 */
public class InheritreeSeed extends Seed {

    public InheritreeSeed(){
        super("Inheritree Seed", '*', true);
    }

    public Crop seedCrop(){
        return new Inheritree();
    }
}
