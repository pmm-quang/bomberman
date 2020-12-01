package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Boms.Flame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private int timeDestroyed = 0;
    private boolean destroyed;

    private Sprite[] brickExploded = new Sprite[] {Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2};

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        destroyed = false;
    }


    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Flame) {
            if (this.rectBox.checkCollision(other.getRectBox())) {
                destroyed = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(double time) {
        destroyed();
    }

    public void destroyed() {
        if (destroyed) {
            int index = timeDestroyed / 10;
            setImg(brickExploded[index].getFxImage());
            timeDestroyed++;
            if (timeDestroyed > 28) {
                this.removed = true;
            }
        }
    }



}
