package pl.ztp.mp3player.libraryStrategy;

import javafx.collections.ObservableList;
import pl.ztp.mp3player.fileFacade.Song;

public interface ISortStrategy {
    void sort(ObservableList<Song> songsList);
}
