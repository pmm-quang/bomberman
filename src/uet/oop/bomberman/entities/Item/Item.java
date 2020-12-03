package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.boundedbox.RectBox;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends Entity {


    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setRectBox(new RectBox(x + 10, y + 10, Sprite.SCALED_SIZE - 20, Sprite.SCALED_SIZE - 20));
    }

    @Override
    public boolean isColliding(Entity other) {
        if(other instanceof Bomber) {
            if (this.rectBox.checkCollision(other.getRectBox())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(double time) {
        if (isColliding(Board.getBomber())) {
            removed = true;
        }
    }

}
