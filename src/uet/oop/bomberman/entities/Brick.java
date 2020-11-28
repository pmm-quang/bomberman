package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public RectBoundedBox boundedBox() {
        return new RectBoundedBox(x + 1, y + 1, x + Sprite.SCALED_SIZE - 2, y + Sprite.SCALED_SIZE - 2);
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }

    @Override
    public void update(double time) {

    }
}