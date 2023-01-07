package pl.javastart.mp3player.strategy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.javastart.mp3player.mp3.Mp3Song;

import java.util.Comparator;

public class SortByLengthStrategy implements ISortStrategy{

    @Override
    public void sort(ObservableList<Mp3Song> songsList) {
        Comparator<Mp3Song> comparator = Comparator.comparing(Mp3Song::getDuration);
        FXCollections.sort(songsList, comparator);
    }
}
