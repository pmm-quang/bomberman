package uet.oop.bomberman.boundedbox;


public class RectBox {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public RectBox(int x, int y, int width, int height) {
        this.minX = x;
        this.minY = y;
        this.maxX = width;
        this.maxY = height;
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


    public void setPosition(int x, int y) {
        int oldX = this.minX;
        int oldY = this.minY;
        setMinX(x);
        setMinY(y);
        setMaxX(this.maxX + (x - oldX));
        setMaxY(this.maxY + (y - oldY));

    }
}
