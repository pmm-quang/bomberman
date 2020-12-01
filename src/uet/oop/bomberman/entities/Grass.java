package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Grass extends Entity{
    public Grass(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public boolean isColliding(Entity other) {
        return false;
    }

    @Override
    public void update(double time) {

    }
}
