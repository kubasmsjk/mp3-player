package pl.ztp.mp3player.factoryMethodComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.ztp.mp3player.mp3.Song;
import pl.ztp.mp3player.player.Mp3PlayerComponent;

public class PlaylistItem extends Mp3PlayerComponent {
    ObservableList<Song> playlistSongs = FXCollections.observableArrayList();
    private String name;

    public PlaylistItem() {
    }

    public PlaylistItem(ObservableList<Song> playlistSongs, String name) {
        this.playlistSongs = playlistSongs;
        this.name = name;
    }

    @Override
    protected void assignListOfSongs() {
    }

    public void setPlaylistSongs(ObservableList<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }

    public ObservableList<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
