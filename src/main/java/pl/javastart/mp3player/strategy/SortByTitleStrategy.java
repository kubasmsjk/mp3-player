package pl.javastart.mp3player.strategy;

import javafx.scene.control.TableView;
import pl.javastart.mp3player.mp3.Mp3Song;

public class SortByTitleStrategy implements ISortStrategy{
    @Override
    public TableView<Mp3Song> sort(TableView<Mp3Song> tableView) {
        System.out.println(tableView.getItems());
        tableView.sort();
        System.out.println();
        return tableView;
    }
}
