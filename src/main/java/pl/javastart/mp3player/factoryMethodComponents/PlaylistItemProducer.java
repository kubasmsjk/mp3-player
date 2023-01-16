package pl.javastart.mp3player.factoryMethodComponents;

import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class PlaylistItemProducer implements IMp3PlayerComponentFactory {
    private static volatile PlaylistItemProducer instance = null;
    PlaylistItem playlistItem = null;
    private PlaylistItemProducer() {
        if (instance != null) {
            throw new RuntimeException("Not allowed. Use getInstance() method.");
        }
    }

    public static PlaylistItemProducer getInstance() {
        if (instance == null) {
            synchronized (PlaylistItemProducer.class) {
                if (instance == null) {
                    instance = new PlaylistItemProducer();
                }
            }
        }
        return instance;
    }

    @Override
    public Mp3PlayerComponent createItem() {
        playlistItem = new PlaylistItem();
        playlistItem.assignListOfSongs();
        return playlistItem;
    }

    public PlaylistItem getPlaylistItem() {
        return playlistItem;
    }
}
