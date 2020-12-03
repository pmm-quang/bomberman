package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.player.Bomber;

public class SpeedItem extends Item {
    private int speedBuff = 5;
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public int getSpeedBuff() {
        return speedBuff;
    }
}
