package pl.javastart.mp3player.strategy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Song;

import java.util.Comparator;

public class SortByLengthStrategy implements ISortStrategy{

    @Override
    public void sort(ObservableList<Song> songsList) {
        Comparator<Song> comparator = Comparator.comparing(Song::getDuration);
        FXCollections.sort(songsList, comparator);
    }
}
