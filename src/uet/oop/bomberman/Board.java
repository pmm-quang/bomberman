package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.lever.FileManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {

    private int level;
    public static int maxBom;
    private static List<MovingEntity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Bom> bomList = new ArrayList<>();
    FileManagement fileManagement;

    public Board() {
        loadLevel(1);
        level = 1;
        maxBom = 3;
    }

    public void update() {

        entities.forEach(g->g.update(BombermanGame.time));
        stillObjects.forEach(g->g.update(BombermanGame.time));
        bomList.forEach(g->g.update(BombermanGame.time));

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
    }
    public void end() {

    }
    public void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        getStillObjects().forEach(g -> g.render(gc));
        getEntities().forEach(g ->g.render(gc));
        getBomList().forEach(g->g.render(gc));


    }

    public void nextLevel() {
        setBomberSpeed(2);

        loadLevel(getLevel() + 1);
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

    public void setBomberSpeed(int speed) {

    }

    public static List<MovingEntity> getEntities() {
        return entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public Bomber getBomber() {
        Iterator<MovingEntity> iterator = entities.iterator();
        MovingEntity entity;
        while(iterator.hasNext()) {
            entity = iterator.next();
            if (entity instanceof Bomber) {
                return (Bomber) entity;
            }
        }
        return null;
    }

    public static List<Bom> getBomList() {
        return bomList;
    }

    public void addBom(Entity bom) {
        System.out.println(bomList.size());
    }

    public int getLevel() {
        return level;
    }
}
