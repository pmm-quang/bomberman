package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class FlameSegment extends Entity {

    protected boolean last;


    public FlameSegment(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    public RectBoundedBox boundedBox() {
        return null;
    }

    @Override
    public void update(double time) {

    }
}
