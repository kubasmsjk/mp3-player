package pl.javastart.mp3player.player;

import pl.javastart.mp3player.templates.MusicLibraryItemProducer;

public class Mp3Player {
    private static volatile Mp3Player instance = null;
    private Mp3PlayerComponent mp3PlayerComponent = new Mp3PlayerComponent();

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

    public void setMp3PlayerComponent(Mp3PlayerComponent mp3PlayerComponent) {
        this.mp3PlayerComponent = mp3PlayerComponent;
    }

    public Mp3PlayerComponent getMp3PlayerComponent() {
        return mp3PlayerComponent;
    }
}