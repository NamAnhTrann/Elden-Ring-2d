package game.grow;

import game.actors.BedOfChaos;

public class Leaf implements TreeComponent {
    @Override
    public int getAttackBonus() {
        return 1;
    }

    @Override
    public void grow(BedOfChaos boss) {
        boss.heal(5);
        System.out.println(boss + " is healed.");
    }
}
