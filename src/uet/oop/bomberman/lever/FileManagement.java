package uet.oop.bomberman.lever;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Item.BombItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.Item.SpeedItem;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.enemy.Doll;
import uet.oop.bomberman.entities.enemy.Oneal;
import uet.oop.bomberman.entities.player.Bomber;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManagement{
    private char[][] map;
    private int level;
    private Board board;
    private static int width;
    private static int height;


    public FileManagement( Board board) {
        this.board = board;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public char[][] getMap() {
        return map;
    }

    public void ReadFromFile(int level) {
        List<String> list = new ArrayList<>();
        try {
            String fileName = "res\\levels\\Level" + level + ".txt";
            FileReader fr =new FileReader(fileName);
            BufferedReader br  = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
            }
            System.out.println("seccess");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arrays = list.get(0).split(" ");
        this.level = Integer.parseInt(arrays[0]);
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
                    case ' ':
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                    case '#': // them wall
                        board.addStillObject(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case 'x': // them Portal;
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addStillObject(new Portal(j, i, Sprite.portal.getFxImage()));
                        board.addStillObject(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case '*': // them brick;
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addStillObject(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'p': //them Bomber
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.createBomber(new Bomber(j, i, Sprite.player_down.getFxImage()));
                        break;
                    case '1': //them Balloon
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addEntity(new Balloon(j, i, Sprite.balloom_left1.getFxImage(), 1));
                        break;
                    case  '2': //them Oneal
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addEntity(new Oneal(j, i, Sprite.oneal_right1.getFxImage(), 1));
                        break;
                    case  '3': //them doll
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addEntity(new Doll(j, i, Sprite.doll_left1.getFxImage(), 1, board.getBomber()));
                        break;
                    case 'b': //them BomItem
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addStillObject(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        board.addStillObject(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 's': //them SpeedItem
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addStillObject(new SpeedItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        board.addStillObject(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'f': //them FlameItem
                        board.addStillObject(new Grass(j, i, Sprite.grass.getFxImage()));
                        board.addStillObject(new FlameItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        board.addStillObject(new Brick(j, i, Sprite.brick.getFxImage()));
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
