package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class FlameSegment extends Entity {

    protected boolean last;


    public FlameSegment(int x, int y, Image img) {
        super(x, y, img);
    }



    @Override
    public boolean isColliding(Entity other) {

        return false;
    }

    @Override
    public void update(double time) {

    }
}
