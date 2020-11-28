package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.player.Bomber;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Enemy> enemies = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Bom> bomList = new ArrayList<>();
    private Bomber bomber;



    public void addEntity(Enemy entity) {
        enemies.add(entity);
    }

    public void addStillObject(Entity entity) {
        stillObjects.add(entity);
    }

    public void createBomber(int x, int y, Image image) {
        bomber = new Bomber(x, y, image);
    }

    public Bomber getBomber() {
        return bomber;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public List<Bom> getBomList() {
        return bomList;
    }
}
