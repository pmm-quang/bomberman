package uet.oop.bomberman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.lever.FileManagement;
import uet.oop.bomberman.sound.Sound;

import javax.swing.*;
import java.awt.*;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;


public class BombermanGame extends Application {

    public static double time;
    private static Board board = new Board();
    public static JPANEL jpanel = new JPANEL(Board.level);
    public static AnchorPane ro = new AnchorPane();
    private int width;
    private int height;
    private GraphicsContext gc;
    private boolean pause = false;
    public static Timeline timer;
    private Canvas canvas;
    Pane pane;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) {
        // Tao Canvas
        width = FileManagement.getWidth();
        height = FileManagement.getHeight();
        canvas = new Canvas(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height + 32);
        gc = canvas.getGraphicsContext2D();
        Text text = new Text("He");
        pane = new Pane(canvas);
        pane.getChildren().add(text);
        Scene scene = new Scene(new BorderPane(pane),600, Sprite.SCALED_SIZE * height + 32);

        //add a scrolling camera
        Rectangle player = new Rectangle(Board.getBomber().getX(), Board.getBomber().getY(), 0, 0);
        pane.getChildren().add(player);
        scrollingCamera(scene, player);

        ro.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE,BLACK));
        jpanel.setPanel();
        pane.getChildren().add(ro);
        //

        Sound.play("soundtrack", AudioClip.INDEFINITE);
        final long timeStart = System.currentTimeMillis();
        KeyBoard.input(scene);
    //    scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();

       timer = new Timeline(new KeyFrame(Duration.seconds(0.032), new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               time = (System.currentTimeMillis() - timeStart) / 1000.0;
               board.update();
               board.render(gc, canvas);
               board.nextLevel(gc, scene, canvas);

               player.setX(Board.getBomber().getX());
               player.setY(Board.getBomber().getY());

           }
       }));

       timer.setCycleCount(Animation.INDEFINITE);
       timer.play();
    }

    /**
     * add a scrolling camera.
     */
    private void scrollingCamera(Scene scene, Rectangle player) {
        Rectangle clip = new Rectangle();

        pane.setMinSize(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        pane.setPrefSize(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        pane.setMinSize(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        //    root.getChildren().add(canvas);
        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());
        clip.xProperty().bind(Bindings.createDoubleBinding(() ->clampRange(player.getX() - scene.getWidth()/2
                , 0, pane.getWidth() - scene.getWidth()), player.xProperty(), scene.widthProperty()));
        clip.yProperty().bind(Bindings.createDoubleBinding(()-> clampRange(player.getY() - scene.getHeight()/2
                , 0, pane.getHeight() - scene.getHeight()), player.yProperty(), scene.heightProperty()));
        pane.setClip(clip);
        pane.translateXProperty().bind(clip.xProperty().multiply(-1));
        pane.translateYProperty().bind(clip.yProperty().multiply(-1));
    }

    private double clampRange(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }


}
