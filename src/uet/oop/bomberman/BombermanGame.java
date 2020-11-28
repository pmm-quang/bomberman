package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
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
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.lever.FileManagement;

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
    private Board board = new Board();
    private FileManagement fileManagement = new FileManagement(board);
    /**
    private List<Enemy> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    Bomber bomberman;
    Enemy balloon = new Balloon(3, 6, Sprite.balloom_left1.getFxImage(), 1);
     */
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
        Scene scene = new Scene(root,Color.GREEN);
     //   scene.setFill(Color.GREEN);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();
        createMap();
        board.getBomber().input(scene, board.getStillObjects());
        final long timeStart = System.currentTimeMillis();
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time = (System.currentTimeMillis() - timeStart) / 1000.0;
                update();
                render();
            }
        };
        animationTimer.start();

    }

    public void createMap() {
        fileManagement.ReadFromFile(1);
        fileManagement.createEntities();

    }

    public void update() {
       board.getBomber().update(board.getStillObjects(), time);
        int l = board.getEnemies().size();
        for(int i = 0; i < l; i++) {
            board.getEnemies().get(i).move(board.getStillObjects(), time);
        }
        /**
        bomberman.move(stillObjects, time);
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).move(stillObjects, time);
            if (entities.get(i) instanceof Oneal) {
                ((Oneal) entities.get(i)).getPosition(bomberman);
            }
        }
         */

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board.getStillObjects().forEach(g -> g.render(gc));
        board.getEnemies().forEach(g ->g.render(gc));
        board.getBomber().render(gc);
        board.getBomber().getBoms().forEach(g->g.render(gc));
        /**
        stillObjects.forEach(g -> g.render(gc));

        entities.forEach(g -> g.render(gc)); // cái đống này lưu tạm bom
       bomberman.render(gc);
         */
    }
}
