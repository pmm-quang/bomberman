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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static double oldGameTime;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    Bomber bomberman;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        scene.setFill(Color.GREEN);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();
        createMap();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());



        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
     //   gameLoop.setDelay(Duration.millis(300));
        bomberman.eventHandler(scene);
        final long timeStart = System.currentTimeMillis();
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double t  = (System.currentTimeMillis() - timeStart) / 1000.0;

                if (bomberman.isHaveBom()) {
                    entities.add(bomberman.DatBom());
                }
                for (int i = 0; i < stillObjects.size(); i++) {
                    bomberman.canMove(stillObjects.get(i));
                }

                bomberman.update(t);
                entities.forEach(g -> g.update(t));

                render();
                update();
            }
        });
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                     Entity object = new Wall(i, j, Sprite.wall.getFxImage());
                    stillObjects.add(object);
                }
                /**
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                 */
            }
        }
    }

    public void update() {
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(gc);
        }
     //   entities.forEach(g -> g.render(gc)); // cái đống này lưu tạm bom
       bomberman.render(gc);
    }
}
