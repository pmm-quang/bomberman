package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bom extends Entity {
    private final int timeToBoom = 3;
    private int timeStart;
    private Sprite[] boms = {Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2};

    public Bom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public RectBoundedBox boundedBox() {
        return null;
    }

    @Override
    public void update(double time) {
        int index = (int)((time % (boms.length * 0.1)) / 0.1);
        img = boms[index].getFxImage();
    }

}
