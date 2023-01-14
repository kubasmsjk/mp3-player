package pl.javastart.mp3player.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItem;
import pl.javastart.mp3player.factoryMethodComponents.Playlists;


public class PlayListPaneController {
    @FXML
    private ControlPaneController playlistToolBarPane;
    @FXML
    private TableView<PlaylistItem> contentTable;

    private static final String PLAYLIST_COLUMN = "Playlist";

    public void initialize() {
        configureTableColumns();
        contentTable.setItems(Playlists.getInstance().getPlaylists());
    }

    private void configureTableColumns() {
        TableColumn<PlaylistItem, String> playlistColumn = new TableColumn<>(PLAYLIST_COLUMN);
        playlistColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playlistColumn.setSortable(false);
        contentTable.getColumns().add(playlistColumn);

    }

    public TableView<PlaylistItem> getContentTable() {
        return contentTable;
    }

}
