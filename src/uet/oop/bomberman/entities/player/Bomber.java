package uet.oop.bomberman.entities.player;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.boundedbox.RectBox;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {

    private boolean haveBom;
    Direction currentDirection;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        spriteUp = new Sprite[] {Sprite.player_up_1, Sprite.player_up_2, Sprite.player_up};
        spriteDown = new Sprite[] {Sprite.player_down_1, Sprite.player_down_2,Sprite.player_down};
        spriteLeft = new Sprite[] {Sprite.player_left_1, Sprite.player_left_2, Sprite.player_left};
        spriteRight = new Sprite[] {Sprite.player_right_1, Sprite.player_right_2, Sprite.player_right};
        spriteDead = new Sprite[] {Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3};
    //    this.rectBox = new RectBox(this.x + 5, this.y + 5, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE - 10);

    }

    @Override
    public void move(int steps, List<Entity> entities) {
        int index = (int) ((BombermanGame.time % (2 * 0.1)) / 0.1);
        if (steps != 0 && currentDirection!= null) {
            switch (currentDirection) {
                case UP:
                    if (!this.canMove(this.x, this.y - steps, entities )) {
                        this.y -= steps;
                        this.setImage(spriteUp[index].getFxImage());
                    }
                    break;
                case DOWN:
                    if (!this.canMove(this.x, this.y + steps, entities)) {
                        this.y += steps;
                        this.setImage(spriteDown[index].getFxImage());
                    }
                    break;
                case LEFT:
                    if (!this.canMove(this.x - steps, this.y, entities )) {
                        this.x -= steps;
                        this.setImage(spriteLeft[index].getFxImage());
                    }
                    break;
                case RIGHT:
                    if (!this.canMove(this.x + steps, this.y, entities)) {
                        this.x += steps;
                        this.setImage(spriteRight[index].getFxImage());
                    }
                    break;
            }
        }
    }

    @Override
    public boolean canMove(int x, int y, List<Entity> other) {
        this.rectBox.setPosition(x, y);
        for (Entity e : other) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
            //    System.out.println(true);
                return true;
            }
        }
        this.rectBox.setPosition(this.x, this.y);
        return false;
    }

    @Override
    public void dead(double start, double time) {
        double t =  time - start;
        if (t > 0 && t <= 1) {
            this.setImage(spriteDead[0].getFxImage());
        } else if (t > 1 && t <= 2) {
            this.setImage(spriteDead[1].getFxImage());
        } else {
            this.setImage(spriteDead[2].getFxImage());
        }
    }

    @Override
    public boolean isLives() {
        return isLive;
    }

    @Override
    public RectBoundedBox boundedBox() {
        return new RectBoundedBox(x + (int)(Sprite.SCALED_SIZE *0.45), y + (int)(Sprite.SCALED_SIZE *0.45)
                , Sprite.SCALED_SIZE - 9, y + Sprite.SCALED_SIZE - 2);
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;
        if (this.getRectBox().checkCollision(other.getRectBox())) System.out.println(true);
        else System.out.println(false);
       return this.getRectBox().checkCollision(other.getRectBox());
    }

    @Override
    public void update(double time) {

    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void input(Scene scene, List<Entity> entities) {
        if (isLives()) {
            scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {

                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.LEFT) {
                                currentDirection = Direction.LEFT;
                            //    move(5,entities);
                            } else if (event.getCode() == KeyCode.RIGHT) {
                                currentDirection = Direction.RIGHT;
                            //    move(5,entities);
                            } else if (event.getCode() == KeyCode.UP) {
                                currentDirection = Direction.UP;
                            //    move(5,entities);
                            } else if (event.getCode() == KeyCode.DOWN) {
                                currentDirection = Direction.DOWN;
                            //    move(5,entities);
                            }
                            if (event.getCode() == KeyCode.A) {
                                haveBom = true;
                            }
                        }
                    });
            scene.setOnKeyReleased(
                    new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent e) {
                            if (e.getCode() == KeyCode.LEFT) {
                                currentDirection = null;
                                img = spriteLeft[2].getFxImage();
                            } else if (e.getCode() == KeyCode.RIGHT) {
                                img = spriteRight[2].getFxImage();
                                currentDirection = null;
                            } else if (e.getCode() == KeyCode.UP) {

                                img = spriteUp[2].getFxImage();
                                currentDirection = null;
                            } else if (e.getCode() == KeyCode.DOWN) {
                                img = spriteDown[2].getFxImage();
                                currentDirection = null;
                            }
                        }
                    });
        }

    }



    public Bom createBom() {
        int realX = this.getX() / Sprite.SCALED_SIZE;
        int realY = this.getY() / Sprite.SCALED_SIZE;
        int countX = this.getX() % Sprite.SCALED_SIZE;
        int countY = this.getY() % Sprite.SCALED_SIZE;
        if (countX > 15) realX ++;
        if (countY > 15) realY ++;
        System.out.println(true);
        Bom bom = new Bom(realX,realY, Sprite.bomb.getFxImage());
        haveBom = false;
        return bom;


    }

    public boolean isHaveBom() {
        return haveBom;
    }
}
