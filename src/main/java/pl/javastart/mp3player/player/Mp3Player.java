package pl.javastart.mp3player.player;

import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import pl.javastart.mp3player.mp3.Mp3Song;

import java.io.File;

public class Mp3Player {
    private ObservableList<Mp3Song> songList;
    private Media media;
    private MediaPlayer mediaPlayer;
    private static volatile Mp3Player instance = null;

    private Mp3Player() {
        if (instance != null) {
            throw new RuntimeException("Niedozwolone. Uzyj metody getInstance()");
        }
    }

    public static Mp3Player getInstance() {
        if (instance == null) {
            synchronized (Mp3Player.class) {
                if (instance == null) {
                    instance = new Mp3Player();
                }
            }
        }
        return instance;
    }

    public void init(ObservableList<Mp3Song> songList) {
        this.songList = songList;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void play() {
        if (mediaPlayer != null && (mediaPlayer.getStatus() == Status.READY || mediaPlayer.getStatus() == Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    public double getLoadedSongLength() {
        if (media != null) {
            return media.getDuration().toSeconds();
        } else {
            return 0;
        }
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public void loadSong(int index) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.stop();
        }
        Mp3Song mp3s = songList.get(index);
        media = new Media(new File(mp3s.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.statusProperty().addListener((observable, oldStatus, newStatus) -> {
            if (newStatus == Status.READY)
                mediaPlayer.setAutoPlay(true);
        });
    }
}