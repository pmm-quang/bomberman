package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.player.Bomber;

public class Portal extends Entity {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public boolean isColliding(Entity other) {
       if (other instanceof Bomber) {
           if(this.rectBox.checkCollision(other.getRectBox())) {
               return true;
           }
       }
        return false;
    }

    @Override
    public void update(double time) {

    }

}
