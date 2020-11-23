package uet.oop.bomberman.lever;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement{
    private static char[][] map;
    private int level;
    private Board board;
    private int width;
    private int height;


    public FileManagement() {
    }

    public void ReadFromFile(int level) {
        List<String> list = new ArrayList<>();
        try {
            String fileName = "res\\levels\\" + level + ".txt";
            FileReader fr =new FileReader(fileName);
            BufferedReader br  = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arrays = list.get(0).split(" ");
        level = Integer.parseInt(arrays[0]);
        height = Integer.parseInt(arrays[1]);
        width = Integer.parseInt(arrays[2]);
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i + 1).charAt(j);
            }
        }

    }

    public void createEntities() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = map[i][j];
                switch (c) {
                    case '#': // them grass
                        board.addEntity(new Wall(j, i, Sprite.grass.getFxImage()));
                        break;
                    case 'x': // them Portal;
                        // TODO: add;
                        break;
                    case '*': // them brick;
                        // TODO: add;
                        break;
                    case 'p': //them Bomber
                        board.addEntity(new Bomber(j, i, Sprite.player_down.getFxImage()));
                        break;
                    case '1': //them Balloon
                        // TODO: add;
                        break;
                    case  '2': //them Oneal
                        // TODO: add;
                        break;
                    case  '3': //them doll
                        // TODO: add;
                        break;
                    case 'b': //them BomItem
                        // TODO: add;
                        break;
                    case 's': //them SpeedItem
                        // TODO: add;
                        break;
                    case 'f': //them FlameItem
                        // TODO: add;
                        break;
                }
            }
        }
    }
    public void SaveWhenOut() {

    }
    public void WriteToFile() {
        String fileName = "res\\levels\\ContinueLevel.txt";
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    fw.write(map[i][j]);
                }
                fw.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
