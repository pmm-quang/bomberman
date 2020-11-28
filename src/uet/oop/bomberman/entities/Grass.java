package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.boundedbox.RectBox;

public class Grass extends Entity {
    public Grass(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
     //   this.rectBox = new RectBox(this.x, this.y, 32, 32);
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
