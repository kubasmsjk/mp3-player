package pl.ztp.mp3player.strategy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.ztp.mp3player.mp3.Song;

import java.util.Comparator;

public class SortByAuthorStrategy implements ISortStrategy {

    @Override
    public void sort(ObservableList<Song> songsList) {
        Comparator<Song> comparator = Comparator.comparing(Song::getAuthor);
        FXCollections.sort(songsList, comparator);
    }
}
