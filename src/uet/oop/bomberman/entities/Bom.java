package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;

public class Bom extends Entity{

    public Bom(int xUnit, int yUnit, Image img) {
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
