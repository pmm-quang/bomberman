package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.player.Bomber;

public abstract class Item extends Entity {

    protected int time = -1;
    protected boolean active = false;
    protected int level;

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
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
    public abstract void buff(Bomber bomber);

    @Override
    public void update(double time) {

    }
}
