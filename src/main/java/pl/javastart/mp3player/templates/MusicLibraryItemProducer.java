package pl.javastart.mp3player.templates;

import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class MusicLibraryItemProducer implements IMp3PlayerFactory{

    private ObservableList<Mp3Song> songList;

    public MusicLibraryItemProducer(ObservableList<Mp3Song> songList) {
        this.songList = songList;
    }

    @Override
    public Mp3PlayerComponent createItem() {
        MusicLibraryItem musicLibraryItem = new MusicLibraryItem();
        musicLibraryItem.fillTemplates();
        return musicLibraryItem;
    }
}
