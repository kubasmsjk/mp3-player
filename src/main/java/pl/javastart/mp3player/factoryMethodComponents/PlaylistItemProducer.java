package pl.javastart.mp3player.factoryMethodComponents;

import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class PlaylistItemProducer implements IMp3PlayerComponentFactory {
    PlaylistItem playlistItem = new PlaylistItem();
    @Override
    public Mp3PlayerComponent createItem() {
        playlistItem.assignListOfSongs();
        return playlistItem;
    }

    public PlaylistItem getPlaylistItem() {
        return playlistItem;
    }
}
