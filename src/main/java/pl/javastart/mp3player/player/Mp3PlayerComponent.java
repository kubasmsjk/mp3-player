package pl.javastart.mp3player.player;

import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pl.javastart.mp3player.mp3.Mp3Song;

import java.io.File;

public abstract class Mp3PlayerComponent {
    public static ObservableList<Mp3Song> songList;
    private Media media;
    private MediaPlayer mediaPlayer;

    public Mp3PlayerComponent() {}


    public void init(ObservableList<Mp3Song> songList) {
        Mp3PlayerComponent.songList = songList;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Media getMedia() {
        return media;
    }

    public void play() {
        if (mediaPlayer != null && (mediaPlayer.getStatus() == MediaPlayer.Status.READY || mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
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

    public static void setSongList(ObservableList<Mp3Song> songList) {
        Mp3PlayerComponent.songList = songList;
    }

    public static ObservableList<Mp3Song> getSongList() {
        return songList;
    }

    public static String getSongTitle(int index) {
        return songList.get(index).getTitle();
    }

    public void loadSong(int index) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        Mp3Song mp3s = songList.get(index);
        this.media = new Media(new File(mp3s.getFilePath()).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
        mediaPlayer.statusProperty().addListener((observable, oldStatus, newStatus) -> {
            if (newStatus == MediaPlayer.Status.READY)
                mediaPlayer.setAutoPlay(true);
        });
    }
}
