package pl.ztp.mp3player.strategy;

import javafx.collections.ObservableList;
import pl.ztp.mp3player.mp3.Song;

public interface ISortStrategy {
    void sort(ObservableList<Song> songsList);
}
