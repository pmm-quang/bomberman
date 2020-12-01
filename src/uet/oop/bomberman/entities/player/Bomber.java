package uet.oop.bomberman.entities.player;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.boundedbox.RectBox;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {

    private List<Bom> boms;
    private boolean createBom;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        boms = new ArrayList<>();
        this.steps = 0;
        this.speed = 2;
        this.hp = 3;
        currentDirection = Direction.DOWN;
        spriteUp = new Sprite[] {Sprite.player_up_1, Sprite.player_up_2, Sprite.player_up};
        spriteDown = new Sprite[] {Sprite.player_down_1, Sprite.player_down_2,Sprite.player_down};
        spriteLeft = new Sprite[] {Sprite.player_left_1, Sprite.player_left_2, Sprite.player_left};
        spriteRight = new Sprite[] {Sprite.player_right_1, Sprite.player_right_2, Sprite.player_right};
        spriteDead = new Sprite[] {Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3};
        setRectBox(new RectBox(this.x + 2, this.y + 2, this.x + Sprite.SCALED_SIZE - 7, this.y + Sprite.SCALED_SIZE - 4));

    }

    @Override
    public void move(double time) {
        int index = (int)((time % (2 * 0.1)) / 0.1);
        int xa = 0, ya = 0;
        if (steps > 0) {
            switch (currentDirection) {
                case UP:
                    ya -= speed;
                    break;
                case DOWN:
                    ya += speed;
                    break;
                case LEFT:
                    xa -= speed;
                    break;
                case RIGHT:
                    xa += speed;
                    break;
            }
            if (!canMove(this.x + xa, this.y + ya)) {
                this.x += xa;
                this.y += ya;
                this.setImage(index);
                steps--;
            } else {
                steps = 0;
            }
        }
    }


    @Override
    public boolean canMove(int x, int y) {
        this.rectBox.setPosition(x, y);
        for (Entity e : Board.getStillObjects()) {
            if (this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
                return true;
            }
        }
        for (Entity e : Board.getEntities()) {
            if (!(e instanceof Bomber) && this.isColliding(e)) {
                this.rectBox.setPosition(this.x, this.y);
                return true;
            }
        }
        this.rectBox.setPosition(this.x, this.y);
        return false;
    }

    @Override
    public void dead() {
        if (!isLive) {
            int index = timeDie / 10;
            setImg(spriteDead[index].getFxImage());
            timeDie++;
            if (timeDie > 28) {
                removed = true;
            }
        }
    }

    @Override
    public boolean isLives() {
        return isLive;
    }

    @Override
    public boolean isColliding(Entity other) {
        if (this.getRectBox().checkCollision(other.getRectBox())) {
            if (other instanceof Item) {
                 return false;
            }
            if (other instanceof Enemy) {
                isLive = false;
                return true;
            }
        }
       return this.getRectBox().checkCollision(other.getRectBox());
    }

    public void input(Scene scene) {
        if (isLives()) {
            scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {

                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.LEFT) {
                                currentDirection = Direction.LEFT;
                                steps = BombermanGame.WIDTH * Sprite.SCALED_SIZE;
                            } else if (event.getCode() == KeyCode.RIGHT) {
                                currentDirection = Direction.RIGHT;
                                steps = BombermanGame.WIDTH * Sprite.SCALED_SIZE;
                            } else if (event.getCode() == KeyCode.UP) {
                                currentDirection = Direction.UP;
                                steps = BombermanGame.WIDTH * Sprite.SCALED_SIZE;
                            } else if (event.getCode() == KeyCode.DOWN) {
                                currentDirection = Direction.DOWN;
                                steps = BombermanGame.WIDTH * Sprite.SCALED_SIZE;
                            }
                            if (event.getCode() == KeyCode.SPACE) {
                                createBom = true;
                                if (createBom() != null)
                                Board.getBomList().add(createBom());
                            }
                        }

                    });
            scene.setOnKeyReleased(
                    new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent e) {
                            if (e.getCode() == KeyCode.LEFT) {
                                steps = 0;
                                img = spriteLeft[2].getFxImage();
                            } else if (e.getCode() == KeyCode.RIGHT) {
                                img = spriteRight[2].getFxImage();
                                steps = 0;
                            } else if (e.getCode() == KeyCode.UP) {
                                img = spriteUp[2].getFxImage();
                                steps = 0;
                            } else if (e.getCode() == KeyCode.DOWN) {
                                img = spriteDown[2].getFxImage();
                                steps = 0;
                            } else if (e.getCode() == KeyCode.SPACE) {
                                createBom = false;
                                Sound.stop("BOM_SET");
                            }
                        }
                    });
        }

    }



    public Bom createBom() {
        if (createBom) {
            if (Board.getBomList().size() >= Board.maxBom) {
                return null;
            }
            int realX = this.getX() / Sprite.SCALED_SIZE;
            int realY = this.getY() / Sprite.SCALED_SIZE;
            int countX = this.getX() % Sprite.SCALED_SIZE;
            int countY = this.getY() % Sprite.SCALED_SIZE;
            if (countX > 15) realX++;
            if (countY > 15) realY++;

            Bom bom = new Bom(realX, realY, Sprite.bomb.getFxImage(),BombermanGame.time);
            for (Entity bom1: Board.getBomList()) {
                if (bom.isColliding(bom1)) {
                    return null;
                }
            }
            Sound.play("BOM_SET");
            return bom;
        }
        return null;
    }

    public boolean isCreateBom() {
        return createBom;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Bom> getBoms() {
        return boms;
    }
}
