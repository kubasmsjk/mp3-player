package pl.javastart.mp3player.player;

import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pl.javastart.mp3player.mp3.Song;

import java.io.File;

public abstract class Mp3PlayerComponent {
    public static ObservableList<Song> songList;
    private Media media;
    private MediaPlayer mediaPlayer;

    //zagraj piosenke
    public void play() {
        if (mediaPlayer != null && (mediaPlayer.getStatus() == MediaPlayer.Status.READY || mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    //zastopuj piosenkę
    public void stop() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    //załaduj piosenkę
    public void loadSong(int index) {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.stop();
        }
        Song mp3s = Mp3PlayerComponent.getSongList().get(index);
        this.media = new Media(new File(mp3s.getFilePath()).toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
        mediaPlayer.statusProperty().addListener((observable, oldStatus, newStatus) -> {
            if (newStatus == MediaPlayer.Status.READY)
                mediaPlayer.setAutoPlay(true);
        });
    }

    //pobierz długośc trwania piosenki
    public double getLoadedSongLength() {
        if (media != null) {
            return media.getDuration().toSeconds();
        } else {
            return 0;
        }
    }

    //ustaw głośność
    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    //pobierz informację o piosencę
    public static String getSongInformations(int index) {
        return "Played song \"" + songList.get(index).getTitle() + "\" author: " + songList.get(index).getAuthor();
    }

    //pobierz aktualną listę piosenek
    public static ObservableList<Song> getSongList() {
        return songList;
    }

    public static void setSongList(ObservableList<Song> songList) {
        Mp3PlayerComponent.songList = songList;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public Media getMedia() {
        return media;
    }

    protected abstract void assignListOfSongs();
}
