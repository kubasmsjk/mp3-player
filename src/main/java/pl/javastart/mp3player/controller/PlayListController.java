package pl.javastart.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.javastart.mp3player.mp3.Mp3Song;

public class PlayListController {
    @FXML
    private ControlPaneController playlistToolBarPane;
    @FXML
    private TableView<Mp3Song> contentTable;

    private static final String PLAYLIST_COLUMN = "Playlist";

    public void initialize() {
        configureTableColumns();
    }

    private void configureTableColumns() {
        TableColumn<Mp3Song, String> playlistColumn = new TableColumn<>(PLAYLIST_COLUMN);
        playlistColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        playlistColumn.setSortable(false);
        contentTable.getColumns().add(playlistColumn);
    }

    public TableView<Mp3Song> getContentTable() {
        return contentTable;
    }
}
