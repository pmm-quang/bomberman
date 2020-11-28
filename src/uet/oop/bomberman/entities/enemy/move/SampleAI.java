package uet.oop.bomberman.entities.enemy.move;

import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.player.Bomber;

import java.util.Random;

public class SampleAI extends EnemyDirection {

     private Enemy enemy;
     private Bomber bomber;

     private int bomberX;
     private int bomberY;
     private int enemyX;
     private int enemyY;

    public SampleAI(Enemy enemy, Bomber bomber) {
        this.enemy = enemy;
        this.bomber = bomber;
    }

    public SampleAI(int bomberX, int bomberY, int enemyX, int enemyY) {
        this.bomberX = bomberX;
        this.bomberY = bomberY;
        this.enemyX = enemyX;
        this.enemyY = enemyY;
        randomDirection = new Random();
    }

    @Override
    public int calculateDirection() {
        int dir = randomDirection.nextInt(2);
        if (dir == 0) {
            int v = rowDirection();
            if (v != -1) {
                return v;
            } else {
                return colDirection();
            }
        } else {
            int h = colDirection();
            if (h != -1) {
                return h;
            } else {
                return rowDirection();
            }
        }
    }

    public int rowDirection() {
        if (bomberX < enemyX) {
            return 2; // enemy di chuyen sang trai.
        } else if (bomberX > enemyX) {
            return 3;
        }
        return -1;
    }
    public int colDirection() {
        if (bomberY < enemyY) {
            return 0;
        } else if (bomberY > enemyY) {
            return 1;
        }
        return -1;
    }
    @Override
    public void getPosition(int bomberX, int bomberY, int enemyX, int enemyY) {
        this.bomberX = bomberX;
        this.bomberY = bomberY;
        this.enemyX = enemyX;
        this.enemyY = enemyY;
    }
}
