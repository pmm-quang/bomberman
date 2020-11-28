package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.enemy.move.EnemyDirection;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class Enemy extends MovingEntity {
    protected boolean alive;
    protected int steps;
    protected EnemyDirection enemyDirection;



    public Enemy(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img);
        this.speed = speed;
        this.alive = true;
        this.timeDie = 1;
        this.steps = 0;
    }

    public void Dead() {
        if (!isLive) {
            setImg(spriteDead[0].getFxImage());
        }
    }

    public abstract void calculateMove(List<Entity> entities, double time);

}
