package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;

public class BombItem extends Item {
    private int maxBom = 5;
    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int getMaxBom() {
        return maxBom;
    }
}
