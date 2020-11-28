package uet.oop.bomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.corba.Bridge;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Enemy;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

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
    public static double oldGameTime;
    public static double startDeadtime;
    public static  double time;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Enemy> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    Bomber bomberman;
    Enemy balloon = new Balloon(3, 6, Sprite.balloom_left1.getFxImage(), 1);
    Enemy oneal = new Oneal(4, 7, Sprite.balloom_left1.getFxImage(),  1);

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * MAP[0].length(), Sprite.SCALED_SIZE *MAP.length);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
     //   scene.setFill(Color.GREEN);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();
        createMap();

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
     //   gameLoop.setDelay(Duration.millis(300));
        final long timeStart = System.currentTimeMillis();
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                time  = (System.currentTimeMillis() - timeStart) / 1000.0;
                bomberman.input(scene, stillObjects);
                bomberman.move(stillObjects);
                for (int i = 0; i < entities.size(); i++) {
                    entities.get(i).calculateMove(stillObjects, time);
                    entities.get(i).move(stillObjects);
                }

                render();

                update();
            }
        });
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    public void createMap() {
        for (int i = 0; i < MAP.length; i++) {
            for (int j = 0; j < MAP[i].length(); j++) {
                char c = MAP[i].charAt(j);
                switch (c) {
                    case '#' :
                        stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '*' :
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'x' :
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'p' :
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        bomberman = new Bomber(j, i, Sprite.player_down.getFxImage());
                        break;
                    case '1' :
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        entities.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage(), 1));
                        break;
                    case '2' :
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        entities.add(new Oneal(j, i, Sprite.oneal_right1.getFxImage(), 1));
                        break;
                    case ' ' :
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                    case 'f' :
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                }

            }
        }
    }

    public void update() {
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));

        entities.forEach(g -> g.render(gc)); // cái đống này lưu tạm bom
       bomberman.render(gc);
    }
}
