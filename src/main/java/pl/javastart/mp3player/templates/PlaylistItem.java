package pl.javastart.mp3player.templates;

import javafx.collections.ObservableList;
import pl.javastart.mp3player.controller.PlaylistCreatorController;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class PlaylistItem extends Mp3PlayerComponent {
    ObservableList<Mp3Song> songList;
    public void assignListOfSongs() {
        System.out.println(songList);
        //Mp3PlayerComponent.setSongList(songList);
    }

}
