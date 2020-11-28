package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.enemy.move.SampleAI;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
    public Doll(int xUnit, int yUnit, Image img, int speed, Bomber bomber) {
        super(xUnit, yUnit, img, speed);
        this.enemyDirection = new SampleAI(bomber.getX(), bomber.getY(), this.x, this.y);
        this.speed = 1;
        this.hp = 1;
        spriteUp = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left3, Sprite.balloom_right2};
        spriteDown = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right3, Sprite.balloom_left2};
        spriteLeft = new Sprite[] {Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3};
        spriteRight = new Sprite[] {Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3};
        spriteDead = new Sprite[] {Sprite.balloom_dead};
    }

    @Override
    public void update(double time) {

    }
}
