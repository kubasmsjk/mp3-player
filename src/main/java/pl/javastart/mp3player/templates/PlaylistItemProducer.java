package pl.javastart.mp3player.templates;

import pl.javastart.mp3player.player.Mp3Player;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class PlaylistItemProducer implements IMp3PlayerFactory{
    @Override
    public Mp3PlayerComponent createItem() {
        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.assignListOfSongs();
        return playlistItem;
    }
}
