package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {

    private Sprite wall = Sprite.wall;

    public Wall(int x, int y, Image img) {
        super(x, y, img);
    //    this.rectBox = new RectBox(this.x + 2, this.y + 2, 32 - 4, 32 -4);
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }

    @Override
    public void update(double time) {

    }

}
