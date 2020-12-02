package uet.oop.bomberman.entities.Boms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bom extends Entity {
    private final int timeToBoom = 3;
    int lenght =  1;
    private double timeStart;
    private boolean bang;
    private Flame[] flames;
    private Sprite[] boms = {Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2};

    public Bom(int x, int y, Image img, double time) {
        super(x, y, img);
        bang = false;
        timeStart = time;
        explode();
    }


    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;
        if (this.getRectBox().checkCollision(other.getRectBox())) {
            if (other instanceof Item) {
                return false;
            }
            if (other instanceof Enemy) {
                return true;
            }
        }
        return this.getRectBox().checkCollision(other.getRectBox());
    }

    @Override
    public void update(double time) {
        if (time - timeStart < 3) {
            int index = (int) ((time % (boms.length * 0.1)) / 0.1);
            img = boms[index].getFxImage();
        } else if (!bang && time - timeStart > 3) {
            bang = true;
        } else if (time - timeStart > 4) {
            removed = true;
        }
    }

    public void updateFlames(double time) {
        for(int i = 0; i < flames.length; i++) {
            flames[i].update(time);
        }
    }

    private void explode() {
        flames = new Flame[4];
        for (int i = 0; i < flames.length; i++) {
            Direction direction = null;
            switch (i) {
                case 0: direction = Direction.LEFT; break;
                case 1: direction = Direction.RIGHT; break;
                case 2: direction = Direction.UP; break;
                case 3: direction = Direction.DOWN; break;
            }
            flames[i] = new Flame(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE, null, BombermanGame.getBombRadius(), direction);
        }
        //   Sound.play("BOM_11_M", 0);
    }



    public boolean isBang() {
        return bang;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!bang) gc.drawImage(img, x, y);
        if (bang) {
            gc.drawImage(Sprite.bomb_exploded2.getFxImage(), x, y);
            for (int i = 0; i < flames.length; i++) {
                flames[i].render(gc);
            }
        }
    }

    public boolean canExploded(int x, int y) {
        for (Entity e : Board.getStillObjects()) {
            if (this.isColliding(e) && e instanceof Brick) {
                return true;
            }
        }
        this.rectBox.setPosition(this.x, this.y);
        return false;
    }



}

