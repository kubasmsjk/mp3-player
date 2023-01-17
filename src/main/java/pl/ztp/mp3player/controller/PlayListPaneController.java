package pl.ztp.mp3player.controller;

import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import pl.ztp.mp3player.factoryMethodComponents.PlaylistItem;
import pl.ztp.mp3player.factoryMethodComponents.Playlists;

public class PlayListPaneController {
    @FXML
    private PlaylistToolBarPaneController playlistToolBarPaneController;
    @FXML
    private TableView<PlaylistItem> contentTable;

    private static final String PLAYLIST_COLUMN = "Playlist";

    //inicjalizacja widoku
    public void initialize() {
        configureTableColumns();
        contentTable.setItems(Playlists.getInstance().getPlaylists());
    }

    //konfiguracja kolumn tabeli
    private void configureTableColumns() {
        TableColumn<PlaylistItem, String> playlistColumn = new TableColumn<>(PLAYLIST_COLUMN);
        playlistColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playlistColumn.setSortable(false);
        contentTable.getColumns().add(playlistColumn);

        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!contentTable.getSelectionModel().getSelectedCells().isEmpty()) {
                playlistToolBarPaneController.getDeleteButton().setDisable(false);
                playlistToolBarPaneController.getEditButton().setDisable(false);
            }
        });
    }

    public TableView<PlaylistItem> getContentTable() {
        return contentTable;
    }

    public PlaylistToolBarPaneController getPlaylistToolBarPaneController() {
        return playlistToolBarPaneController;
    }
}
