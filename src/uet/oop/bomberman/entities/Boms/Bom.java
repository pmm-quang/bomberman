package uet.oop.bomberman.entities.Boms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public class Bom extends Entity {
    private final int timeToBoom = 3;
    private static int lenghtFlame =  1;
    double timeBang = 0;
    private double timeStart;
    private boolean bang;
    private Flame[] flames;
    private Sprite[] boms = {Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2};

    public Bom(int x, int y, Image img, double time) {
        super(x, y, img);
        bang = false;
        timeStart = time;
        flames = new Flame[4];
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

    public boolean canExploded() {
        for (Bom e : Board.getBomList()) {
            for (int i = 0; i < e.getFlames().length; i++) {
                if (e.getFlames()[i] != null) {
                    if (this.isColliding(e.getFlames()[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void update(double time) {
        if (time - timeStart < 3 && !canExploded()) {
            setImg(Sprite.movingSprite(Sprite.bomb_1, Sprite.bomb_2, 2, time).getFxImage());
        } else {
            if (!bang) {
                bang = true;
                explode();
                Sound.play("BOM_11_M", 0);
            } else {
                timeBang ++;
                setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, 10, (int)timeBang).getFxImage());
                updateFlames(timeBang);
               if (timeBang > 28) {
                   removed = true;
               }
            }
        }
    }

    public void updateFlames(double time) {
        for(int i = 0; i < flames.length; i++) {
            flames[i].update(time);
        }
    }

    private void explode() {
        for (int i = 0; i < flames.length; i++) {
            Direction direction = null;
            switch (i) {
                case 0: direction = Direction.LEFT; break;
                case 1: direction = Direction.RIGHT; break;
                case 2: direction = Direction.UP; break;
                case 3: direction = Direction.DOWN; break;
            }
            flames[i] = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, null, lenghtFlame, direction);
        }
    }



    public boolean isBang() {
        return bang;
    }

    @Override
    public void render(GraphicsContext gc) {

        gc.drawImage(img, x, y);
        if (bang) {
            for (int i = 0; i < flames.length; i++) {
                flames[i].render(gc);
            }
        }
    }

    public Flame[] getFlames() {
        return flames;
    }

    public static void setLenghtFlame(int newlenghtFlame) {
        lenghtFlame = newlenghtFlame;
    }
}
