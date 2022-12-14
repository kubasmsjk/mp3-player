package pl.javastart.mp3player.strategy;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import pl.javastart.mp3player.mp3.Mp3Song;

public interface ISortStrategy {
    void sort(ObservableList<Mp3Song> songsList);
}
