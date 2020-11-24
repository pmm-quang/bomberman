package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.entities.Boms.Bom;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bomber extends Entity {

    private Sprite[] bomberUp = {Sprite.player_up_1, Sprite.player_up_2, Sprite.player_up};
    private Sprite[] bomberDown = {Sprite.player_down_1, Sprite.player_down_2,Sprite.player_down};
    private Sprite[] bomberLeft = {Sprite.player_left_1, Sprite.player_left_2, Sprite.player_left};
    private Sprite[] bomberRight = {Sprite.player_right_1, Sprite.player_right_2, Sprite.player_right};
    private Sprite[] bomberDead = {Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3};
    private boolean isKeyLeft;
    private boolean isKeyRight;
    private boolean isKeyUp;
    private boolean isKeyDown;
    private boolean haveBom;
    private boolean isLive;
    private   ArrayList<String> input = new ArrayList<String>();
    int index = 0;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        isKeyLeft = false;
        isKeyRight = false;
        isKeyUp = false;
        isKeyDown = false;
        haveBom = false;
       isLive = true;
    }

    @Override
    public RectBoundedBox boundedBox() {
        return new RectBoundedBox(x + 1, y + 1, x + Sprite.SCALED_SIZE - 9, y + Sprite.SCALED_SIZE - 2);
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setKeyLeft(boolean keyLeft) {
        isKeyLeft = keyLeft;
    }

    public void eventHandler(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {

                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.LEFT) {
                            isKeyLeft = true;
                        } else if (event.getCode() == KeyCode.RIGHT) {
                            isKeyRight = true;
                        } else if (event.getCode() == KeyCode.UP) {
                            isKeyUp = true;
                        } else if (event.getCode() == KeyCode.DOWN) {
                            isKeyDown = true;
                        }
                        if (event.getCode() == KeyCode.A) {
                            haveBom = true;
                        }
                    }
                });
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e) {
                        if (e.getCode() == KeyCode.LEFT) {
                            isKeyLeft = false;
                            index = 0;
                            img = bomberLeft[2].getFxImage();
                        } else if (e.getCode() == KeyCode.RIGHT) {
                            isKeyRight = false;
                            index = 0;
                            img = bomberRight[2].getFxImage();
                        } else if (e.getCode() == KeyCode.UP) {
                            isKeyUp = false;
                            index = 0;
                            img = bomberUp[2].getFxImage();
                        } else if (e.getCode() == KeyCode.DOWN) {
                            isKeyDown = false;
                            index = 0;
                            img = bomberDown[2].getFxImage();
                        }
                    }
                });

    }


    @Override
    public void update(double time) {
        int index = (int)((time % (2 * 0.1)) / 0.1);
        if (isKeyLeft) {
            x = x - 1;
           this.img = bomberLeft[index].getFxImage();

        } else if (isKeyRight) {
            x = x + 1;
            this.img = bomberRight[index].getFxImage();

        } else if (isKeyUp) {
            y = y - 1;
            this.img = bomberUp[index].getFxImage();

        } else if (isKeyDown) {
            y = y + 1;
            this.img = bomberDown[index].getFxImage();
        }
    }

    public void canMove(Entity other) {
        if (this.isColliding(other)) {
            if (this.boundedBox().getMinY() != other.boundedBox().getMaxY()
                    && this.boundedBox().getMaxY() != other.boundedBox().getMinY()) {

                if (this.boundedBox().getMaxX() == other.boundedBox().getMinX()) {
                    isKeyRight = false;
                } else if (this.boundedBox().getMinX() == other.boundedBox().getMaxX()) {
                    isKeyLeft = false;
                }

            } else if (this.boundedBox().getMinX() != other.boundedBox().getMaxX()
                    && this.boundedBox().getMaxX() != other.boundedBox().getMinX()){

                if (this.boundedBox().getMinY() == other.boundedBox().getMaxY()) {
                    isKeyUp = false;
                } else if (this.boundedBox().getMaxY() == other.boundedBox().getMinY()) {
                    isKeyDown = false;
                }
            }
        }
    }

    public void isLives(Entity e) {
        if (this.isColliding(e)) {
            if (e instanceof Flame || e instanceof Bom) {
                isLive = false;
            }
        }
    }

    public Bom DatBom() {
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
