package uet.oop.bomberman.entities.Boms;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bom extends Entity {
    private final int timeToBoom = 3;
    private double timeStart;
    private boolean bang;
    private Sprite[] boms = {Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2};

    public Bom(int x, int y, Image img, double time) {
        super(x, y, img);
        bang = false;
        timeStart = time;
    }


    @Override
    public boolean isColliding(Entity other) {
        return  (this.rectBox.checkCollision(other.getRectBox()));
    }

    @Override
    public void update(double time) {
        int index = (int)((time % (boms.length * 0.1)) / 0.1);
        img = boms[index].getFxImage();
        if (time - timeStart >= 3) {
            removed = true;
        }
    }

    public boolean isBang() {
        return bang;
    }
}
