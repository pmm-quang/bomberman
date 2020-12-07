package uet.oop.bomberman;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import static javafx.scene.paint.Color.WHITE;

public class JPANEL extends AnchorPane{
    public Label labelLevel;

    public Label labelTime;

    public Label labelPoint;

    public Label labelLives;

    public JPANEL(int level) {
        labelLevel = new Label("LEVEL "+ level + " - BOMBERMAN");
        labelLevel.setLayoutX(50);
        labelLevel.setLayoutY(1);
        labelLevel.setFont(Font.font(18));
        labelLevel.setTextFill(WHITE);

    }
    public void setPanel() {
        BombermanGame.ro.getChildren().add(labelLevel);
    }

    public void setLevel(int t) {
        labelLevel.setText("LEVEL : " + t);
    }

}
