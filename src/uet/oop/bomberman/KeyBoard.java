package uet.oop.bomberman;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import uet.oop.bomberman.direction.Direction;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.lever.FileManagement;
import uet.oop.bomberman.sound.Sound;

public class KeyBoard {

    public static Direction direction = Direction.LEFT;
    private static int count = 0; // count = 1 : game pause

    public static void input(Scene scene) {
            scene.setOnKeyPressed(
                    new EventHandler<KeyEvent>() {

                        @Override
                        public void handle(KeyEvent event) {
                            if (event.getCode() == KeyCode.LEFT) {
                                Board.getBomber().setDirection(Direction.LEFT);
                                Board.getBomber().setSteps(1000);
                            } else if (event.getCode() == KeyCode.RIGHT) {
                                Board.getBomber().setDirection(Direction.RIGHT);
                                Board.getBomber().setSteps(1000);
                            } else if (event.getCode() == KeyCode.UP) {
                                Board.getBomber().setDirection(Direction.UP);
                                Board.getBomber().setSteps(1000);
                            } else if (event.getCode() == KeyCode.DOWN) {
                                Board.getBomber().setDirection(Direction.DOWN);
                                Board.getBomber().setSteps(1000);
                            }
                            if (event.getCode() == KeyCode.SPACE) {
                                Board.getBomber().setCreateBom(true);
                                if (Board.getBomber().createBom() != null) {
                                    Board.addBom(Board.getBomber().createBom());
                                }
                            }
                            if (event.getCode() == KeyCode.P) {
                                count ++;
                                if (count == 2) count = 0;
                                if (count == 1) {
                                    BombermanGame.timer.pause();

                                } else {
                                    BombermanGame.timer.play();
                                }
                            }
                        }

                    });
            scene.setOnKeyReleased(
                    new EventHandler<KeyEvent>() {
                        public void handle(KeyEvent e) {
                            if (e.getCode() == KeyCode.LEFT) {
                                Board.getBomber().setSteps(0);
                                Board.getBomber().setImg(Sprite.player_left.getFxImage());
                            } else if (e.getCode() == KeyCode.RIGHT) {
                                Board.getBomber().setSteps(0);
                                Board.getBomber().setImg(Sprite.player_right.getFxImage());
                            } else if (e.getCode() == KeyCode.UP) {
                                Board.getBomber().setImg(Sprite.player_up.getFxImage());
                                Board.getBomber().setSteps(0);
                            } else if (e.getCode() == KeyCode.DOWN) {
                                Board.getBomber().setImg(Sprite.player_down.getFxImage());
                                Board.getBomber().setSteps(0);
                            } else if (e.getCode() == KeyCode.SPACE) {
                                Board.getBomber().setCreateBom(false);
                             //   Sound.stop("BOM_SET");
                            }
                        }
                    });

    }
}
