package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.enemy.move.DirectionRandom;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {

    public Balloon(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        this.enemyDirection = new DirectionRandom();
        this.speed = 1;
        this.hp = 1;
        this.MAX_STEPS = Sprite.SCALED_SIZE * 1;
        spriteUp = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left3, Sprite.balloom_right2};
        spriteDown = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right3, Sprite.balloom_left2};
        spriteLeft = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
        spriteRight = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3};
        spriteDead = new Sprite[] {Sprite.balloom_dead};
    }

}
