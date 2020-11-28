package uet.oop.bomberman.boundedbox;

public class RectBoundedBox {
    private int minX, minY, maxX, maxY;

    public RectBoundedBox(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public boolean isColliding(RectBoundedBox other) {
        return maxX >= other.minX && minX <= other.maxX
                && maxY >= other.minY && minY <= other.maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
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
    public void setPostion(int x, int y) {
        setMinX(x);
        setMinY(y);
        setMaxX(this.maxX + (x - this.minX));
        setMaxY(this.minY + (y - this.minY));
    }
}
