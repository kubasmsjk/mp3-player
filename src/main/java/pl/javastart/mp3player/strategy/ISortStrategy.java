package pl.javastart.mp3player.strategy;

import javafx.scene.control.TableView;
import pl.javastart.mp3player.mp3.Mp3Song;

public interface ISortStrategy {
    TableView<Mp3Song> sort(TableView<Mp3Song> tableView);
}
