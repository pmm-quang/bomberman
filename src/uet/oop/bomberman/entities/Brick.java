package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Boms.Bom;
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
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(double time) {
        for (Bom e : Board.getBomList()) {
            for (int i = 0; i < e.getFlames().length; i++) {
                if (e.getFlames()[i] != null) {
                    if (this.isColliding(e.getFlames()[i])) {
                        destroyed = true;
                    }
                }
            }
        }
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

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
