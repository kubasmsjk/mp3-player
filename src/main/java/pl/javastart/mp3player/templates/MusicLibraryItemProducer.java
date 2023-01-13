package pl.javastart.mp3player.templates;

import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class MusicLibraryItemProducer implements IMp3PlayerFactory {
    private static volatile MusicLibraryItemProducer instance = null;

    private MusicLibraryItemProducer() {
        if (instance != null) {
            throw new RuntimeException("Not allowed. Use getInstance() method.");
        }
    }

    public static MusicLibraryItemProducer getInstance() {
        if (instance == null) {
            synchronized (MusicLibraryItemProducer.class) {
                if (instance == null) {
                    instance = new MusicLibraryItemProducer();
                }
            }
        }
        return instance;
    }

    @Override
    public Mp3PlayerComponent createItem() {
        MusicLibraryItem musicLibraryItem = new MusicLibraryItem();
        musicLibraryItem.assignListOfSongs();
        return musicLibraryItem;
    }
}
