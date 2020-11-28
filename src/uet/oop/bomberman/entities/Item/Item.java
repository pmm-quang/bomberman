package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
