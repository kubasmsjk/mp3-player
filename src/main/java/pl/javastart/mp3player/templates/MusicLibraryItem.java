package pl.javastart.mp3player.templates;

import javafx.collections.ObservableList;
import pl.javastart.mp3player.controller.MainController;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class MusicLibraryItem extends Mp3PlayerComponent {
    ObservableList<Mp3Song> librarySongs = MainController.getListOfSongs();

    public void assignListOfSongs() {
        songList = librarySongs;
    }

}
