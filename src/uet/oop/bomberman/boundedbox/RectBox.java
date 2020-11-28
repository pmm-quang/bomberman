package uet.oop.bomberman.boundedbox;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.graphics.Sprite;

public class RectBox {
    int minX;
    int minY;
    int maxX;
    int maxY;
//    RectBox rectBox;

    public RectBox(int x, int y, int width, int height) {
        this.minX = x;
        this.minY = y;
        this.maxX = width;
        this.maxY = height;
    //    this.rectBox = new RectBox(minX, minY, maxX, maxY);
    }



    public boolean checkCollision(RectBox other) {
        return maxX >= other.minX && minX <= other.maxX
                && maxY >= other.minY && minY <= other.maxY;
    }
    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    /**
    public void setPosition(int x, int y, double reductionPercent) {
        this.minX = x + (int)(Sprite.SCALED_SIZE * reductionPercent);
        this.minY = y + (int)(Sprite.SCALED_SIZE * reductionPercent);
        boundary = new Rectangle2D(this.minX, this.minY, maxX, maxY);
    }
     */
    public void setPosition(int x, int y) {
        setMinX(x);
        setMinY(y);
        setMaxX(minX + 30);
        setMaxY(minY + 30);

    }
}
