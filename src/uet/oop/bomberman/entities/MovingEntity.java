package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Boms.Flame;
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
    protected int hp;
    protected Direction currentDirection;
    protected int MAX_STEPS;

    public MovingEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        isLive = true;
        MAX_STEPS = 0;
    }

    public abstract void move(double time);

    public abstract boolean canMove(int x, int y);

    public void setImage(int index) {
        switch (currentDirection) {
            case UP:
                this.setImg(spriteUp[index].getFxImage());
                break;
            case DOWN:
                this.setImg(spriteDown[index].getFxImage());
                break;
            case RIGHT:
                this.setImg(spriteRight[index].getFxImage());
                break;
            case LEFT:
                this.setImg(spriteLeft[index].getFxImage());
                break;
        }
    }

    public abstract void dead();

    public abstract boolean isLives();

    public int getHp() {
        return hp;
    }

    public void setSteps(int step) {
        steps = step;
    }

    public void update(double time) {
        if (isLives()) {
            move(time);
        }
        for (Bom e : Board.getBomList()) {
            for (int i = 0; i < e.getFlames().length; i++) {
                if (e.getFlames()[i] != null) {
                    if (this.isColliding(e.getFlames()[i])) {
                        this.hp--;
                        System.out.println(true);
                    }
                }
            }
            if (isColliding(e)) {
                if (e.isBang()) {
                    hp--;
                }
            }
        }
        dead();
    }
}
