package game.actors;

import game.behaviours.NormalSelector;
import game.grow.Growable;
import game.behaviours.BOCBehaviours;
import game.items.BareFist;
import game.grow.Branch;
import game.grow.TreeComponent;

public class BedOfChaos extends NonPlayableCharacter implements Growable {


    private TreeComponent root;

    public BedOfChaos() {
        super("Bed Of Chaos", 'T', 1000);
        this.root = new Branch(true);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addBehaviour(0, new BOCBehaviours(root, this));
        this.setIntrinsicWeapon(new BareFist());

    }


    @Override
    public void growSelf() {
        root.grow(this);
    }
}
