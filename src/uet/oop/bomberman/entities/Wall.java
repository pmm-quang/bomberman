package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    private Sprite wall = Sprite.wall;

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public RectBoundedBox boundedBox() {
        return new RectBoundedBox(x + 1, y + 1, x + Sprite.SCALED_SIZE - 2, y + Sprite.SCALED_SIZE - 2);
    }

    @Override
    public void update(double time) {

    }
}
