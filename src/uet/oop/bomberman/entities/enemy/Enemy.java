package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Boms.Flame;
import uet.oop.bomberman.entities.Boms.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.enemy.move.EnemyDirection;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

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
            if (timeDie == 1) {
                Sound.play("AA126_11", 0);
            }
            if (timeDie < 3) {
                setImg(spriteDead[0].getFxImage());
            } else {
                removed = true;
            }
            timeDie ++;
        }
    }


    @Override
    public boolean canMove(int x, int y) {
        this.rectBox.setPosition(x, y);
        for (Bom e : Board.getBomList()) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);

                return true;
            }

        }
        for (Entity e : Board.getStillObjects()) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
                return true;
            }
        }
        this.rectBox.setPosition(this.x, this.y);
        return false;
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;


        return this.getRectBox().checkCollision(other.getRectBox());
    }

    @Override
    public void move(double time) {
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
        if (!canMove(this.x + xa, this.y + ya)) {
            this.x += xa;
            this.y += ya;
            this.setImage(index);
            steps --;
        } else {
            steps = 0;
        }
    }

}
