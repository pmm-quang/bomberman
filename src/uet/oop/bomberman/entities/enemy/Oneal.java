package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.entities.Boms.FlameSegment;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        spriteUp = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left3, Sprite.balloom_right2};
        spriteDown = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right3, Sprite.balloom_left2};
        spriteLeft = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
        spriteRight = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3};
        spriteDead = new Sprite[] {Sprite.balloom_dead};
    }

    @Override
    public void calculateMove(List<Entity> entities, double time) {

    }

    @Override
    public void move(List<Entity> entities) {

    }

    @Override
    public boolean canMove(int x, int y, List<Entity> other) {
        return false;
    }

    @Override
    public void dead(double start, double time) {

    }

    @Override
    public boolean isLives() {
        return false;
    }

    @Override
    public RectBoundedBox boundedBox() {
        return new RectBoundedBox(x + 1, y + 1, x + Sprite.SCALED_SIZE - 2, y + Sprite.SCALED_SIZE - 2);
    }


    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;
        if (this.boundedBox().isColliding(other.boundedBox())) {
            if (other instanceof FlameSegment || other instanceof Bomber) {
                isLive = false;
                return false;
            }
        }
        return this.boundedBox().isColliding(other.boundedBox());
    }

    @Override
    public void update(double time) {

    }

}
