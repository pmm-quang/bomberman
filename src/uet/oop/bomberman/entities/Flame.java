package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;

public class Flame extends Entity {

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public RectBoundedBox boundedBox() {
        return null;
    }

    @Override
    public void update(double time) {

    }
}
