package uet.oop.bomberman.entities.player;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.boundedbox.RectBox;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {

    private List<Bom> boms;
    private int maxNumberBom;
    private boolean createBom;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        boms = new ArrayList<>();
        this.maxNumberBom = 3;
        this.steps = 0;
        this.speed = 1;
        this.hp = 3;
        currentDirection = Direction.DOWN;
        spriteUp = new Sprite[] {Sprite.player_up_1, Sprite.player_up_2, Sprite.player_up};
        spriteDown = new Sprite[] {Sprite.player_down_1, Sprite.player_down_2,Sprite.player_down};
        spriteLeft = new Sprite[] {Sprite.player_left_1, Sprite.player_left_2, Sprite.player_left};
        spriteRight = new Sprite[] {Sprite.player_right_1, Sprite.player_right_2, Sprite.player_right};
        spriteDead = new Sprite[] {Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3};
        setRectBox(new RectBox(this.x + 1, this.y + 1, this.x + Sprite.SCALED_SIZE - 7, this.y + Sprite.SCALED_SIZE - 2));

    }

    @Override
    public void move(List<Entity> entities, double time) {
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
            if (!canMove(this.x + xa, this.y + ya, entities)) {
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
            this.setImg(spriteDead[0].getFxImage());
        } else if (t > 1 && t <= 2) {
            this.setImg(spriteDead[1].getFxImage());
        } else if (t > 2 && t < 3) {
            this.setImg(spriteDead[2].getFxImage());
        } else {
            hp --;
            if (hp > 0) {
                isLive = true;
            }
        }
    }

    @Override
    public boolean isLives() {
        return isLive;
    }

    @Override
    public boolean isColliding(Entity other) {
        if (other instanceof Grass) return false;
        if (this.getRectBox().checkCollision(other.getRectBox())) {
            if (other instanceof Item) {

            }
        }
       return this.getRectBox().checkCollision(other.getRectBox());
    }

    @Override
    public void update(double time) {

    }

    public void update(List<Entity> entities,double time) {
        move(entities, time);
        createBom(time);
        for (int i = 0; i < boms.size(); i++) {
            if (boms.get(i).isBang()) {
                boms.remove(i);
                i--;
            }
        }
        for (int i = 0; i < boms.size(); i++) {
            boms.get(i).update(time);
        }
    }

    public void input(Scene scene, List<Entity> entities) {
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
                            }
                        }
                    });
        }

    }



    public void createBom(double time) {
        if (createBom) {
            if (boms.size() == maxNumberBom) {
                createBom = false;
                System.out.println(false);
                return;
            }
            int realX = this.getX() / Sprite.SCALED_SIZE;
            int realY = this.getY() / Sprite.SCALED_SIZE;
            int countX = this.getX() % Sprite.SCALED_SIZE;
            int countY = this.getY() % Sprite.SCALED_SIZE;
            if (countX > 15) realX++;
            if (countY > 15) realY++;
            for (int i = 0; i < boms.size(); i++) {
                if (boms.get(i).getX() == realX && boms.get(i).getY() == realY) {
                    createBom = false;
                    System.out.println(false);
                    return;
                }
            }
            System.out.println(true);
            Bom bom = new Bom(realX, realY, Sprite.bomb.getFxImage(), time);
            boms.add(bom);
            createBom = false;
        }

    }

    public boolean isCreateBom() {
        return createBom;
    }

    public void setMaxNumberBom(int maxNumberBom) {
        this.maxNumberBom = maxNumberBom;
    }

    public List<Bom> getBoms() {
        return boms;
    }
}
