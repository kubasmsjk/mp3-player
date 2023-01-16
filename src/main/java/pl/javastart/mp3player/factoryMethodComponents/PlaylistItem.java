package pl.javastart.mp3player.factoryMethodComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class PlaylistItem extends Mp3PlayerComponent {
    ObservableList<Mp3Song> playlistSongs  = FXCollections.observableArrayList();
    private String name;

    public PlaylistItem() {
    }

    public PlaylistItem(ObservableList<Mp3Song> playlistSongs, String name) {
        this.playlistSongs = playlistSongs;
        this.name = name;
    }

    @Override
    protected void assignListOfSongs() {
    }

    public void setPlaylistSongs(ObservableList<Mp3Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public ObservableList<Mp3Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
