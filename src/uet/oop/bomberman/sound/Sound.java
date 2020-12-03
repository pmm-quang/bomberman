package uet.oop.bomberman.sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {

    public static void play(String name, int loops) {
        String path = "res/sound/"+ name +".wav";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip mp = new AudioClip(new File(path).toURI().toString());
        mp.setCycleCount(loops);

        mp.play();
    }

    public static void stop(String name) {
        String path = "res/sound/"+ name +".wav";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip mp = new AudioClip(new File(path).toURI().toString());
        mp.stop();
    }
}
