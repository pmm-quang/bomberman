package uet.oop.bomberman.entities.enemy.move;

import java.util.Random;

public abstract class EnemyDirection {
    protected int direction;
    protected Random randomDirection;
    public abstract int calculateDirection();

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public void getPosition(int xa, int ya, int xb, int yb) {

    }
}
