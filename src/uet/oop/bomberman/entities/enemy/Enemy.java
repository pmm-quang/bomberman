package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;


public abstract class Enemy extends MovingEntity {
    protected boolean alive;
    protected int direction;
    protected Random random = new Random();
    protected int steps;



    public Enemy(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img);
        this.speed = speed;
        this.alive = true;
        this.timeDie = 1;
    }

    public void resetDirection() {
        moveDown = false;
        moveLeft = false;
        moveRight = false;
        moveUp = false;
    }

    public void Dead() {
        if (!isLive) {
            setImg(spriteDead[0].getFxImage());
        }
    }
    public abstract void calculateDirection(Entity other, double time);

}
