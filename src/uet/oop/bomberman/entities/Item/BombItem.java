package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.player.Bomber;

public class BombItem extends Item {
    private int maxBom = 5;
    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff(Bomber bomber) {

        removed = true;
    }

    public int getMaxBom() {
        return maxBom;
    }
}
