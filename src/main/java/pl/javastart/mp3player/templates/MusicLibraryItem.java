package pl.javastart.mp3player.templates;


import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class MusicLibraryItem extends Mp3PlayerComponent {
    ObservableList<Mp3Song> songList;
    public void fillTemplates(ObservableList<Mp3Song> songList) {
        Mp3PlayerComponent.setSongList(songList);
    }
}
