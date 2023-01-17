package pl.ztp.mp3player.factoryMethodComponents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Playlists {
    private final ObservableList<PlaylistItem> playlists = FXCollections.observableArrayList();

    private static volatile Playlists instance = null;

    private Playlists() {
        if (instance != null) {
            throw new RuntimeException("Not allowed. Use getInstance() method.");
        }
    }

    public static Playlists getInstance() {
        if (instance == null) {
            synchronized (Playlists.class) {
                if (instance == null) {
                    instance = new Playlists();
                }
            }
        }
        return instance;
    }

    public ObservableList<PlaylistItem> getPlaylists() {
        return playlists;
    }
}
