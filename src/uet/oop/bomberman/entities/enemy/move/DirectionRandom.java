package uet.oop.bomberman.entities.enemy.move;

import java.util.Random;

public class DirectionRandom extends EnemyDirection {

    public DirectionRandom() {
        randomDirection = new Random();
        direction = 0;
    }

    @Override
    public int calculateDirection() {
        return randomDirection.nextInt(4);
    }
}
