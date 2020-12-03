package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.BombItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.lever.FileManagement;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int level;
    public static int maxBom;
    public static boolean nextLv;
    private static List<MovingEntity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Bom> bomList = new ArrayList<>();
    private static Bomber bomber;
    FileManagement fileManagement;

    public Board() {
        loadLevel(1);
        level = 1;
        maxBom = 3;
        nextLv = false;
    }

    public void update() {

        entities.forEach(g->g.update(BombermanGame.time));
        stillObjects.forEach(g->g.update(BombermanGame.time));
        bomList.forEach(g->g.update(BombermanGame.time));
        bomber.update(BombermanGame.time);
        removeEntity();

    }

    public void removeEntity() {
        for (int i = 0; i < entities.size(); i++) {
            if (stillObjects.get(i).isRemoved()) {
                if (stillObjects.get(i) instanceof Bomber) {
                    end();
                }
                stillObjects.remove(i);
                i--;
            }
        }

        for (int i = 0; i < stillObjects.size(); i ++) {
            if (stillObjects.get(i).isRemoved()) {
                stillObjects.remove(i);
                i--;
            }
        }

        for (int i = 0; i < bomList.size(); i++) {
            if (bomList.get(i).isRemoved()) {
                bomList.remove(i);
                i--;
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
                i--;
            }
        }
    }
    public void end() {
        if (bomber.isRemoved()) {
            BombermanGame.timer.stop();
        }
    }
    public void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        getStillObjects().forEach(g -> g.render(gc));
        getBomList().forEach(g->g.render(gc));
        getEntities().forEach(g ->g.render(gc));
        bomber.render(gc);

    }

    public void nextLevel(GraphicsContext gc, Scene scene, Canvas canvas) {
        if (nextLv && entities.isEmpty()) {
            setBomberSpeed(2);
            setLengthFlame(1);
            setMaxBom(3);
            loadLevel(getLevel() + 1);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            BombermanGame.timer.stop();
            BombermanGame.timer.setDelay(Duration.seconds(3));
            setNextLv(false);
            System.out.println(nextLv);
            BombermanGame.timer.play();
        }
    }

    public void loadLevel(int level) {
        entities.clear();
        stillObjects.clear();

        fileManagement = new FileManagement(this);
        fileManagement.ReadFromFile(level);
        fileManagement.createEntities();

    }

    public void addEntity(MovingEntity entity) {
        entities.add(entity);
    }

    public void addStillObject(Entity entity) {
        stillObjects.add(entity);
    }

    private static void setBomberSpeed(int speed) {
        bomber.setSpeed(speed);
    }
    private static void setMaxBom(int number) {
        maxBom = number;
    }

    private static void setLengthFlame(int length) {
        Bom.setLenghtFlame(length);
    }

    public static List<MovingEntity> getEntities() {
        return entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static Bomber getBomber() {
       return bomber;
    }
    public void createBomber(Bomber bombers) {
        bomber = bombers;
    }

    public static List<Bom> getBomList() {
        return bomList;
    }

    public static void addBom(Bom bom) {
        System.out.println(bomList.size());
        bomList.add(bom);
    }

    public int getLevel() {
        return level;
    }

    public static void setNextLv(boolean nextLv) {
        Board.nextLv = nextLv;
    }

    public static void buff(Item item) {
        if (item instanceof SpeedItem) {
            setBomberSpeed(((SpeedItem) item).getSpeedBuff());
        } else if (item instanceof BombItem) {
            setMaxBom(((BombItem) item).getMaxBom());
        } else if (item instanceof FlameItem) {
            setLengthFlame(((FlameItem) item).getLengthMaxFlame());
        }
    }


}
