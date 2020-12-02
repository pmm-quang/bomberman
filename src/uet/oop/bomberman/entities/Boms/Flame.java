package uet.oop.bomberman.entities.Boms;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Flame extends Entity {

    private final Sprite[] BomBangMid = {Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2};
    private final Sprite[] BomBangLeft = {Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2};
    private final Sprite[] BomBangRight = {Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2};
    private final Sprite[] BomBangUp = {Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2};
    private final Sprite[] BomBangDown = {Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2};

    protected Board board;
    int lenght;
    protected Direction direction;
    private  int radius;
    protected int xOrigin, yOrigin;
    protected List<FlameSegment> flameSegments = new ArrayList<>();

    public Flame(int xUnit, int yUnit, Image img, int radius, Direction direction) {
        super(xUnit, yUnit, img);
        lenght = 1;
        this.direction = direction;
        this.radius = radius;
        createFlameSegments();
    }

    public boolean checkBomToPlayer(){
        return false;
    }

    public void createFlameSegments() {
        boolean last = false;
        int xa = this.getX();
        int ya = this.getY();
        for (int i = 0; i < radius; i++) {
            boolean collision = false;
            if(i == radius - 1) {
                last = true;
            }
            switch (direction) {
                case UP: ya-=32; break;
                case DOWN: ya+=32; break;
                case RIGHT: xa+=32; break;
                case LEFT: xa-=32; break;
            }
            FlameSegment e = new FlameSegment(xa / Sprite.SCALED_SIZE, ya / Sprite.SCALED_SIZE, null, direction, last);
            for (Entity entity : Board.getStillObjects()) {
                if (e.isColliding(entity)) {
                    collision = true;
                    return;

                }
            }
            if (!collision) {
                flameSegments.add(e);
                //    System.out.println("yes");
            }
        }
    }

    private int lenghtFlame() {
        int lenght = 0;
        int xa = getX();
        int ya = getY();
        for (int i = lenght; i > 0; i--) {
            switch (direction) {
                case UP: ya-=32; break;
                case DOWN: ya+=32; break;
                case RIGHT: xa+=32; break;
                case LEFT: xa-=32; break;
            }
            Entity e = new FlameSegment(xa, ya, null, direction, false);
            for (Entity entity : Board.getStillObjects()) {
                if (e.isColliding(entity)) {
                    lenght --;
                }
            }
        }
        return lenght;
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        for (int i = 0; i < flameSegments.size(); i++) {
            flameSegments.get(i).render(gc);
        }
    }
    @Override
    public void update(double time) {
    }

}
