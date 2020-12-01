package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.player.Bomber;

public class SpeedItem extends Item {
    private int speedBuff = 4;
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff(Bomber bomber) {

    }


    public int getSpeedBuff() {
        return speedBuff;
    }
}
