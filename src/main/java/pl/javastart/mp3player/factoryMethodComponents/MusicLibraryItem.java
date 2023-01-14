package pl.javastart.mp3player.factoryMethodComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.javastart.mp3player.controller.MainController;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class MusicLibraryItem extends Mp3PlayerComponent {
    ObservableList<Mp3Song> librarySongs = FXCollections.observableArrayList();
    private static volatile MusicLibraryItem instance = null;

    private MusicLibraryItem() {
        if (instance != null) {
            throw new RuntimeException("Not allowed. Use getInstance() method.");
        }
    }

    public static MusicLibraryItem getInstance() {
        if (instance == null) {
            synchronized (MusicLibraryItem.class) {
                if (instance == null) {
                    instance = new MusicLibraryItem();
                }
            }
        }
        return instance;
    }

    @Override
    protected void assignListOfSongs() {
        songList = librarySongs;
    }

    public ObservableList<Mp3Song> getLibrarySongs() {
        return librarySongs;
    }
}
