package game.items;

import game.grounds.Crop;
import game.grounds.Bloodrose;

/**
 * Class that represents Blood rose seed class
 */
public class BloodroseSeed extends Seed {

    /**
     * Constructor
     */
    public BloodroseSeed(){
        super("Bloodrose Seed", '*', true);
    }

    public Crop seedCrop(){
        return new Bloodrose();
    }
}
