package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
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

    @Override
    public boolean isLives() {
        if (hp <= 0) {
            isLive = false;
            setSteps(0);
        }
        return isLive;
    }


    @Override
    public void dead() {
        if (!isLives()) {
            if (timeDie < 15) {
                setImg(spriteDead[0].getFxImage());
            } else {
                removed = true;
            }
        }
    }

    @Override
    public boolean canMove(int x, int y, List<Entity> other) {
        this.rectBox.setPosition(x, y);
        for (Entity e : other) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
                //    System.out.println(true);
                return true;
            }
        }
        this.rectBox.setPosition(this.x, this.y);
        return false;
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;
        if (other instanceof FlameSegment) {
            if (hp > 0) {
                hp --;
            }
            return false;
        }
        return this.getRectBox().checkCollision(other.getRectBox());
    }

    @Override
    public void move(List<Entity> entities, double time) {
        int index = (int)((time%(2 * 0.1)) / 0.1);
        if (steps <= 0) {
            enemyDirection.setDirection(enemyDirection.calculateDirection());
            steps = this.MAX_STEPS;
        }
        int xa = 0, ya = 0;
        switch (enemyDirection.getDirection()) {
            case 0 :
                currentDirection = Direction.UP;
                ya -= speed;
                break;
            case 1 :
                currentDirection = Direction.DOWN;
                ya += speed;
                break;
            case 2 :
                currentDirection = Direction.LEFT;
                xa -= speed;
                break;
            case 3 :
                currentDirection = Direction.RIGHT;
                xa += speed;
                break;
        }
        if (!canMove(this.x + xa, this.y + ya, entities)) {
            this.x += xa;
            this.y += ya;
            this.setImage(index);
            steps --;
        } else {
            steps = 0;
        }
    }

}
