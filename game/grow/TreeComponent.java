package game.grow;

import game.actors.BedOfChaos;

public interface TreeComponent {
    int getAttackBonus();
    void grow(BedOfChaos boss);
}
