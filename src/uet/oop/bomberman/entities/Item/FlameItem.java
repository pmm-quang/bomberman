package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.player.Bomber;

public class FlameItem extends Item {
    private int lenghtMaxFlame = 3;

    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff(Bomber bomber) {

    }

    public int getLenghtMaxFlame() {
        return lenghtMaxFlame;
    }
}
