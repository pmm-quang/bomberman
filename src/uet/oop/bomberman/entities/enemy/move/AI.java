package uet.oop.bomberman.entities.enemy.move;

import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.player.Bomber;

public class AI extends EnemyDirection {

     private Enemy enemy;
     private Bomber bomber;

    public AI(Enemy enemy, Bomber bomber) {
        this.enemy = enemy;
        this.bomber = bomber;
    }

    @Override
    public int calculateDirection() {
        return 0;
    }

    public int rowDirection() {
        return 0;
    }
    public int colDirection() {
        return 0;
    }
}
