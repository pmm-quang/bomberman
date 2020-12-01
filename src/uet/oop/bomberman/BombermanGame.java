package uet.oop.bomberman;

import com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;

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
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static  double time;
    private GraphicsContext gc;
    Timeline timer;
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
        canvas = new Canvas(Sprite.SCALED_SIZE * MAP[0].length(), Sprite.SCALED_SIZE *MAP.length);
        gc = canvas.getGraphicsContext2D();

        //add a scrolling camera
        Rectangle player = new Rectangle(board.getBomber().getX(), board.getBomber().getY(), 0, 0);
        // Tao root container
    //    Group root = new Group();
        Pane pane = new Pane(canvas);
        pane.getChildren().add(player);
        pane.setMinSize(Sprite.SCALED_SIZE * MAP[0].length(), Sprite.SCALED_SIZE *MAP.length );
        pane.setPrefSize(Sprite.SCALED_SIZE * MAP[0].length(), Sprite.SCALED_SIZE *MAP.length);
        pane.setMinSize(Sprite.SCALED_SIZE * MAP[0].length(), Sprite.SCALED_SIZE *MAP.length);
    //    root.getChildren().add(canvas);
        Rectangle clip = new Rectangle();
        // Tao scene
        Scene scene = new Scene(new BorderPane(pane),600, Sprite.SCALED_SIZE *MAP.length,Color.GREEN);
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
        // Them scene vao stage
        stage.setScene(scene);
     //   Sound.play("soundtrack");
        stage.show();
        final long timeStart = System.currentTimeMillis();
        timer = new Timeline();
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.setAutoReverse(true);
        if (board.getBomber() != null) {
            board.getBomber().input(scene);
        }
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                time = (System.currentTimeMillis() - timeStart) / 1000.0;
                board.update();
                player.setX(board.getBomber().getX());
                player.setY(board.getBomber().getY());
                board.render(gc, canvas);
            }
        });
        timer.getKeyFrames().add(keyFrame);
        timer.play();
    }

}
