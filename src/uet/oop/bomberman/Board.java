package uet.oop.bomberman;

import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Entity> entities = new ArrayList<>();
    private List<Bom> bomList = new ArrayList<>();


    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
