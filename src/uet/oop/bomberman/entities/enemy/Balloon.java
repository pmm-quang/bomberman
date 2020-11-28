package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.enemy.move.DirectionRandom;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Balloon extends Enemy {

    public Balloon(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        this.enemyDirection = new DirectionRandom();
        this.speed = 1;
        spriteUp = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left3, Sprite.balloom_right2};
        spriteDown = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right3, Sprite.balloom_left2};
        spriteLeft = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
        spriteRight = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3};
        spriteDead = new Sprite[] {Sprite.balloom_dead};
    }

    @Override
    public void calculateMove(List<Entity> entities, double time) {
        if (steps <= 0) {
            enemyDirection.setDirection(enemyDirection.calculateDirection());
            steps = 32;
        }
        switch (enemyDirection.getDirection()) {
            case 0 :
                currentDirection = Direction.UP;
                break;
            case 1 :
                currentDirection = Direction.DOWN;
                break;
            case 2 :
                currentDirection = Direction.LEFT;
                break;
            case 3 :
                currentDirection = Direction.RIGHT;
                break;
        }
        move(entities);
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
    public void dead(double start, double time) {
        if ( time - start <= 1) {
            this.setImg(spriteDead[0].getFxImage());
        } else {
            this.setImg(null);
        }
    }

    @Override
    public boolean isLives() {
        return isLive;
    }

    @Override
    public RectBoundedBox boundedBox() {
        return new RectBoundedBox(x + 1, y + 1, x + Sprite.SCALED_SIZE - 2, y + Sprite.SCALED_SIZE - 2);
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;
        if (this.getRectBox().checkCollision(other.getRectBox())) System.out.println(true);
        else System.out.println(false);
        return this.getRectBox().checkCollision(other.getRectBox());
    }

    @Override
    public void update(double time) {

    }
}
