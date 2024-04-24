package bjp.utility;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    public static void startGame() {
        URL resource = SoundEffects.class.getResource("/audio/game-start.wav");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);  // Set volume to 30%
        mediaPlayer.play();
    }
    public static void endGame() { // method to for adding the game end sound effect
        URL resource = SoundEffects.class.getResource("/audio/game_end.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);  // Set volume to 30%
        mediaPlayer.play();
    }

    public static void bgmGame() { // method to for adding a background music
        URL resource = SoundEffects.class.getResource("");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);  // Set volume to 30%
        mediaPlayer.play();
    }


    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        SoundEffects.mediaPlayer = mediaPlayer;
    }
}
