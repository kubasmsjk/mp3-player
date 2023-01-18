package pl.ztp.mp3player.libraryStrategy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.ztp.mp3player.fileFacade.Song;

import java.util.Comparator;

public class SortByTitleStrategy implements ISortStrategy {
    @Override
    public void sort(ObservableList<Song> songsList) {
        Comparator<Song> comparator = Comparator.comparing(Song::getTitle);
        FXCollections.sort(songsList, comparator);
    }
}
