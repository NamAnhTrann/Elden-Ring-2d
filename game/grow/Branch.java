package game.grow;

import game.actors.BedOfChaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Branch implements TreeComponent {
    private final List<TreeComponent> children = new ArrayList<>();
    private final Random rand = new Random();

    private final boolean isRoot;

    public Branch() {
        this(false); // default: not root
    }

    public Branch(boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public int getAttackBonus() {
        int total = isRoot ? 0 : 3; // root contributes 0, other branches contribute 3
        for (TreeComponent child : children) {
            total += child.getAttackBonus();
        }
        return total;
    }

    @Override
    public void grow(BedOfChaos boss) {
        System.out.println("It grows a branch...\n");
        if (rand.nextBoolean()) {
            System.out.println("Branch is growing...");
            Branch branch = new Branch(); // child branches are not root
            children.add(branch);
            branch.grow(boss);
        } else {
            System.out.println("It grows a leaf...\n");
            Leaf leaf = new Leaf();
            children.add(leaf);
            leaf.grow(boss);
        }
    }
}
