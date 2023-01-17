package pl.ztp.mp3player.factoryMethodComponents;

import pl.ztp.mp3player.player.Mp3PlayerComponent;

public class MusicLibraryItemProducer implements IMp3PlayerComponentFactory {
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
        MusicLibraryItem musicLibraryItem = MusicLibraryItem.getInstance();
        musicLibraryItem.assignListOfSongs();
        return musicLibraryItem;
    }
}
