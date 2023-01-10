package pl.javastart.mp3player.player;

import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Mp3Song;

public class Mp3Player {
    private static volatile Mp3Player instance = null;
    private Mp3PlayerComponent mp3PlayerComponent;
    private Mp3Player() {
        if (instance != null) {
            throw new RuntimeException("Not allowed. Use getInstance() method.");
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

    public Mp3PlayerComponent getMp3PlayerComponent() {
        mp3PlayerComponent = new Mp3PlayerComponent() {
            @Override
            public void init(ObservableList<Mp3Song> songList) {
                super.init(songList);
            }
        };
        return mp3PlayerComponent;
    }
}