package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {
    protected boolean last;
    private Direction direction;
    private final Sprite[] BomBangMid = {Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2};
    private final Sprite[] BomBangLastLeft = {Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2};
    private final Sprite[] BomBangLastRight = {Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2};
    private final Sprite[] BomBangLastUp = {Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2};
    private final Sprite[] BomBangLastDown = {Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2};

    public FlameSegment(int xUnit, int yUnit, Image img, Direction direction, boolean last) {
        super(xUnit, yUnit, img);
        this.last = last;
        this.direction = direction;
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
        switch (direction) {
            case UP:
                if (!this.last) {
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical,Sprite.explosion_vertical1, Sprite.explosion_vertical2, 10, (int)time).getFxImage();
                } else {
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, 10, (int)time).getFxImage();
                }
                break;
            case DOWN:
                if (!this.last) {
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical,Sprite.explosion_vertical1, Sprite.explosion_vertical2, 10, (int)time ).getFxImage();
                } else {
                    this.img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, 10, (int)time).getFxImage();
                }
                break;
            case LEFT:
                if (!this.last) {
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, 10, (int)time).getFxImage();
                } else {
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, 10, (int)time).getFxImage();
                }
                break;
            case RIGHT:
                if (!this.last) {
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, 10, (int)time).getFxImage();

                } else {
                    this.img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, 10, (int)time).getFxImage();
                }
                break;
        }
    }
}
