package uet.oop.bomberman.entities.enemy.move;

import java.util.Random;

public abstract class EnemyMove {
    protected Random random = new Random();

    public abstract int calculateDirection();


}
