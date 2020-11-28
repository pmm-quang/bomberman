package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class MovingEntity extends Entity{
    protected Sprite[] spriteUp;
    protected Sprite[] spriteDown;
    protected Sprite[] spriteLeft;
    protected Sprite[] spriteRight;
    protected Sprite[] spriteDead;
    protected int speed;
    protected int steps;
    protected int timeDie;
    protected boolean isLive;
    protected Direction currentDirection;

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isLive = true;
    }

    public void move(List<Entity> entities) {
        int index = (int) ((BombermanGame.time % (2 * 0.1)) / 0.1);
        if (steps > 0) {
            switch (currentDirection) {
                case UP:
                    if (!this.canMove(this.x, this.y - speed, entities)) {
                        this.y -= speed;
                        steps --;
                        this.setImg(spriteUp[index].getFxImage());
                    } else {
                        steps = 0;
                    }
                    break;
                case DOWN:
                    if (!this.canMove(this.x, this.y + speed, entities)) {
                        this.y += speed;
                        steps--;
                        this.setImg(spriteDown[index].getFxImage());
                    } else {
                        steps = 0;
                    }
                    break;
                case LEFT:
                    if (!this.canMove(this.x - speed, this.y, entities)) {
                        this.x -= speed;
                        steps--;
                        this.setImg(spriteLeft[index].getFxImage());
                    } else {
                        steps = 0;
                    }
                    break;
                case RIGHT:
                    if (!this.canMove(this.x + speed, this.y, entities)) {
                        this.x += speed;
                        steps--;
                        this.setImg(spriteRight[index].getFxImage());
                    } else {
                        steps = 0;
                    }
                    break;
            }
        }
    }

    public abstract boolean canMove(int x, int y, List<Entity> other);


    public abstract void dead(double start, double time);

    public abstract boolean isLives();

}
