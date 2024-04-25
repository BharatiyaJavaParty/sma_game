package bjp.utility;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundEffects {
    private static MediaPlayer mediaPlayer;
    private static MediaPlayer mediaPlayer2;

    public static void playGemCollectedSound() {
        URL resource = SoundEffects.class.getResource("/audio/gem_collected.wav");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
    }

    public static void playTransportSound() {
        URL resource = SoundEffects.class.getResource("/audio/got_station.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
    }

    public static void newLevel() {
        URL resource = SoundEffects.class.getResource("/audio/level_completed.wav");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
    }

    public static void startGame() {
        URL resource = SoundEffects.class.getResource("/audio/game-start.wav");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();
    }
    public static void endGame() {
        URL resource = SoundEffects.class.getResource("/audio/game_end.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }

    public static void bgmGame() {
        URL resource = SoundEffects.class.getResource("/audio/game_theme.mp3");
        Media media = new Media(resource.toString());
        mediaPlayer2 = new MediaPlayer(media);
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer2.setVolume(0.8);
        mediaPlayer2.play();
    }
    public static void endbgmGame() {
        mediaPlayer2.stop();
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
        SoundEffects.mediaPlayer = mediaPlayer;
    }
}
