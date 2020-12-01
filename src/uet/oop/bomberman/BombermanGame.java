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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.lever.FileManagement;

import java.util.concurrent.atomic.AtomicInteger;


public class BombermanGame extends Application {

    public static final String[] MAP =
            {"###############################"
            ,"#p     ** *  1 * 2 *  * * *   #"
            ,"# # # #*# # #*#*# # # #*#*#*# #"
            ,"#  x*     ***  *  1   * 2 * * #",
            "# # # # # #*# # #*#*# # # # #*#",
            "#f         x **  *  *   1     #",
            "# # # # # # # # # #*# #*# # # #",
            "#*  *      *  *      *        #",
            "# # # # #*# # # #*#*# # # # # #",
            "#*    **  *       *           #",
            "# #*# # # # # # #*# # # # # # #",
            "#           *   *  *          #",
            "###############################"};

    public static  double time;
    private int width;
    private int height;
    private GraphicsContext gc;
    private boolean pause = false;
    public static Timeline timer;
    private Canvas canvas;
    private Board board = new Board();
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    private double clampRange(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        width = FileManagement.getWidth();
        height = FileManagement.getHeight();
        canvas = new Canvas(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        gc = canvas.getGraphicsContext2D();

        //add a scrolling camera
        Rectangle player = new Rectangle(Board.getBomber().getX(), Board.getBomber().getY(), 0, 0);
        // Tao root container
    //    Group root = new Group();
        Pane pane = new Pane(canvas);
        Scene scene = new Scene(new BorderPane(pane),500, Sprite.SCALED_SIZE *MAP.length);
        pane.getChildren().add(player);

        pane.setMinSize(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        pane.setPrefSize(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
        pane.setMinSize(Sprite.SCALED_SIZE * width, Sprite.SCALED_SIZE * height);
    //    root.getChildren().add(canvas);
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());
        clip.xProperty().bind(Bindings.createDoubleBinding(() ->clampRange(player.getX() - scene.getWidth()/2
                , 0, pane.getWidth() - scene.getWidth()), player.xProperty(), scene.widthProperty()));
        clip.yProperty().bind(Bindings.createDoubleBinding(()-> clampRange(player.getY() - scene.getHeight()/2
                , 0, pane.getHeight() - scene.getHeight()), player.yProperty(), scene.heightProperty()));
        pane.setClip(clip);
        pane.translateXProperty().bind(clip.xProperty().multiply(-1));
        pane.translateYProperty().bind(clip.yProperty().multiply(-1));
        //

     //   Sound.play("soundtrack");
        final long timeStart = System.currentTimeMillis();

        KeyBoard.input(scene);

       timer = new Timeline(new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               time = (System.currentTimeMillis() - timeStart) / 1000.0;
                   board.update();
                   board.render(gc, canvas);
                   player.setX(Board.getBomber().getX());
                   player.setY(Board.getBomber().getY());
           }
       }));
       timer.setCycleCount(Animation.INDEFINITE);
       timer.play();
       scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();

    }

}
