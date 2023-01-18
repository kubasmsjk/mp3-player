package pl.ztp.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.ztp.mp3player.fileFacade.Song;

public class ContentPaneController {
    @FXML
    private ControlPaneController controlPaneController;
    @FXML
    private PlayListPaneController playlistPaneController;
    @FXML
    private TableView<Song> contentTable;

    private static final String TITLE_COLUMN = "Title";
    private static final String AUTHOR_COLUMN = "Author";
    private static final String ALBUM_COLUMN = "Album";

    //inicjalizacja widoku
    public void initialize() {
        configureTableColumns();
    }

    //konfiguracja kolumn tabeli
    private void configureTableColumns() {
        TableColumn<Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);

        TableColumn<Song, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setSortable(false);

        TableColumn<Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        albumColumn.setSortable(false);

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
        contentTable.getColumns().add(albumColumn);
    }

    public TableView<Song> getContentTable() {
        return contentTable;
    }

    public ControlPaneController getControlPaneController() {
        return controlPaneController;
    }

    public PlayListPaneController getPlayListPaneController() {
        return playlistPaneController;
    }
}