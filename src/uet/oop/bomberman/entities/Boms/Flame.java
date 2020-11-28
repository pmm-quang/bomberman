package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {

    private final Sprite[] BomBangMid = {Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2};
    private final Sprite[] BomBangLeft = {Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2};
    private final Sprite[] BomBangRight = {Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2};
    private final Sprite[] BomBangUp = {Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2};
    private final Sprite[] BomBangDown = {Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2};

    protected Board board;
    protected int direction;
    private  int radius;
    protected FlameSegment[] flameSegments = new FlameSegment[0];

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean checkBomToPlayer(){
        return false;
    }


    @Override
    public RectBoundedBox boundedBox() {
        return null;
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }

    @Override
    public void update(double time) {

    }
}
