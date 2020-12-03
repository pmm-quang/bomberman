package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.enemy.move.DirectionRandom;
import uet.oop.bomberman.entities.enemy.move.SampleAI;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        this.enemyDirection = new DirectionRandom();
        this.speed = 1;
        this.hp = 2;
        this.MAX_STEPS = Sprite.SCALED_SIZE * 2;
        spriteUp = new Sprite[] {Sprite.oneal_left1, Sprite.oneal_left3, Sprite.oneal_right2};
        spriteDown = new Sprite[] {Sprite.oneal_right1, Sprite.oneal_right3, Sprite.oneal_left2};
        spriteLeft = new Sprite[] {Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3};
        spriteRight = new Sprite[] {Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3};
        spriteDead = new Sprite[] {Sprite.balloom_dead};
    }

}
