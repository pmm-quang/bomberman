package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.boundedbox.RectBox;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;
    protected RectBox rectBox;



    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.rectBox = new RectBox(this.x + 1, this.y + 1, this.x + Sprite.SCALED_SIZE - 2, this.y + Sprite.SCALED_SIZE - 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public RectBox getRectBox() {
        return rectBox;
    }

    public void setRectBox(RectBox rectBox) {
        this.rectBox = rectBox;
    }

    public abstract boolean isColliding(Entity other);


    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update(double time);

}
