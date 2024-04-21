package bjp.utility;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class SoundEffects {
    private static MediaPlayer mediaPlayer;

    public static void playGemCollectedSound() {
        URL resource = SoundEffects.class.getResource("/audio/gem_collected.wav");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void playTransportSound() {
        URL resource = SoundEffects.class.getResource("/audio/got_station.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    public static void newLevel() {
        URL resource = SoundEffects.class.getResource("/audio/level_completed.wav");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}

