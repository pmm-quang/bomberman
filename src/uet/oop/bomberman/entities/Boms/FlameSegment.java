package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {
    protected boolean last;
    public FlameSegment(int xUnit, int yUnit, Image img, Direction direction, boolean last) {
        super(xUnit, yUnit, img);
        this.last = last;
        switch (direction) {
            case UP:
                if (!this.last) {
                    setImg(Sprite.explosion_vertical2.getFxImage());
                } else {
                    setImg(Sprite.explosion_vertical_top_last2.getFxImage());
                }
                break;
            case DOWN:
                if (!this.last) {
                    setImg(Sprite.explosion_vertical2.getFxImage());
                } else {
                    setImg(Sprite.explosion_vertical_down_last2.getFxImage());
                }
                break;
            case LEFT:
                if (!this.last) {
                    setImg(Sprite.explosion_horizontal2.getFxImage());
                } else {
                    setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
                }
                break;
            case RIGHT:
                if (!this.last) {
                    setImg(Sprite.explosion_horizontal2.getFxImage());
                } else {
                    setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
                }
                break;
        }
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Brick || other instanceof Wall) {
            if (this.rectBox.checkCollision(other.getRectBox())) {
                return true;
            }
        }
        return false;
    }
    

    public Image getImg() {
        return this.img;
    }


    @Override
    public void update(double time) {

    }
}
