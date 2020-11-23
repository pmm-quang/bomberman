package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.boundedbox.RectBoundedBox;
import uet.oop.bomberman.graphics.Sprite;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {

    private Sprite[] bomberUp = {Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2};
    private Sprite[] bomberDown = {Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2};
    private Sprite[] bomberLeft = {Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2};
    private Sprite[] bomberRight = {Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2};
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
                            img = bomberLeft[0].getFxImage();
                        } else if (e.getCode() == KeyCode.RIGHT) {
                            isKeyRight = false;
                            index = 0;
                            img = bomberRight[0].getFxImage();
                        } else if (e.getCode() == KeyCode.UP) {
                            isKeyUp = false;
                            index = 0;
                            img = bomberUp[0].getFxImage();
                        } else if (e.getCode() == KeyCode.DOWN) {
                            isKeyDown = false;
                            index = 0;
                            img = bomberDown[0].getFxImage();
                        }
                        if (e.getCode() == KeyCode.A) {
                            haveBom = false;
                        }
                    }
                });

    }


    @Override
    public void update(double time) {
        if (isKeyLeft) {
            x--;
            index++;
            int tmp  = index / 8;
           switch (tmp) {
               case 0: this.img = bomberLeft[1].getFxImage(); break;
               case 1: this.img = bomberLeft[2].getFxImage(); break;
           }
            if (index == 15) index = 0;
        } else if (isKeyRight) {
            x++;
            index++;
            int tmp  = index / 8;
            switch (tmp) {
                case 0: this.img = bomberRight[1].getFxImage(); break;
                case 1: this.img = bomberRight[2].getFxImage(); break;
            }
            if (index == 15) index = 0;
        } else if (isKeyUp) {
            y--;
            index++;
            int tmp  = index / 8;
            switch (tmp) {
                case 0: this.img = bomberUp[1].getFxImage(); break;
                case 1: this.img = bomberUp[2].getFxImage(); break;
            }
            if (index == 15) index = 0;
        } else if (isKeyDown) {
            y++;
            index++;
            int tmp  = index / 8;
            switch (tmp) {
                case 0: this.img = bomberDown[1].getFxImage(); break;
                case 1: this.img = bomberDown[2].getFxImage(); break;
            }
            if (index == 15) index = 0;
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
        int middleOfBomberX = this.getX() - Sprite.DEFAULT_SIZE;
        int middleOfBomberY = this.getY() - Sprite.DEFAULT_SIZE;
        int countX = middleOfBomberX % Sprite.SCALED_SIZE;
        int countY = middleOfBomberY % Sprite.SCALED_SIZE;
        int xBoom = middleOfBomberX / Sprite.SCALED_SIZE + Sprite.SCALED_SIZE;
        int yBoom = middleOfBomberY / Sprite.SCALED_SIZE + Sprite.SCALED_SIZE;
        System.out.println(true);
        return new Bom(xBoom, yBoom, Sprite.bomb.getFxImage());


    }

    public boolean isHaveBom() {
        return haveBom;
    }
}
