package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public abstract class MovingEntity extends Entity{
    protected Sprite[] spriteUp;
    protected Sprite[] spriteDown;
    protected Sprite[] spriteLeft;
    protected Sprite[] spriteRight;
    protected Sprite[] spriteDead;
    protected boolean moveLeft;
    protected boolean moveRight;
    protected boolean moveUp;
    protected boolean moveDown;
    protected int speed;
    protected int timeDie;
    protected boolean isLive;
    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        moveDown = false;
        moveLeft = false;
        moveRight = false;
        moveUp = false;
        isLive = true;
        speed = 1;
    }
    public abstract void move(int steps, List<Entity> entities);

    public abstract boolean canMove(int x, int y, List<Entity> other);


    public abstract void dead(double start, double time);

    public abstract boolean isLives();


}
