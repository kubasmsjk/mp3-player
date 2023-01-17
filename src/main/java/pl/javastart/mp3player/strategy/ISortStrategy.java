package pl.javastart.mp3player.strategy;

import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Song;

public interface ISortStrategy {
    void sort(ObservableList<Song> songsList);
}
