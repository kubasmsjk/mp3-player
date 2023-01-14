package pl.javastart.mp3player.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.stage.Stage;
import pl.javastart.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItem;
import pl.javastart.mp3player.factoryMethodComponents.Playlists;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3PlayerComponent;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItemProducer;

import java.util.Objects;

public class PlaylistCreatorController {
    @FXML
    private Label libraryLabel;
    @FXML
    private Label playlistLabel;
    @FXML
    private TableView<Mp3Song> libraryTable;
    @FXML
    private TableView<Mp3Song> playlistTable;
    @FXML
    private Button createButton;
    @FXML
    private TextField nameTextField;

    private static final String TITLE_COLUMN = "Title";
    private static final String AUTHOR_COLUMN = "Author";
    private static final String ALBUM_COLUMN = "Album";

    public void initialize() {
        createButton.setDisable(true);
        libraryLabel.setText("Library");
        playlistLabel.setText("Playlist");
        configureTableColumns(libraryTable);
        configureTableColumns(playlistTable);
        configureTableClick();
        libraryTable.setItems(FXCollections.observableArrayList(MusicLibraryItem.getSongList()));
        createPlaylist();
    }

    private void configureTableColumns(TableView<Mp3Song> table) {
        TableColumn<Mp3Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);

        TableColumn<Mp3Song, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setSortable(false);

        TableColumn<Mp3Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        albumColumn.setSortable(false);
        table.getColumns().add(titleColumn);
        table.getColumns().add(authorColumn);
        table.getColumns().add(albumColumn);
    }

    private void configureTableClick() {
        libraryTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!libraryTable.getSelectionModel().getSelectedCells().isEmpty()) {
                Mp3Song mp3Song = libraryTable.getSelectionModel().getSelectedItem();
                playlistTable.getItems().add(mp3Song);
                libraryTable.getItems().remove(mp3Song);
                createButton.setDisable(false);
            }
        });

        playlistTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!playlistTable.getSelectionModel().getSelectedCells().isEmpty()) {
                Mp3Song mp3Song = playlistTable.getSelectionModel().getSelectedItem();
                libraryTable.getItems().add(mp3Song);
                playlistTable.getItems().remove(mp3Song);
                if (playlistTable.getItems().size() == 0) {
                    createButton.setDisable(true);
                }
            }
        });
    }

    private void createPlaylist() {
        createButton.setOnAction(event -> {
            nameTextField.setText(nameTextField.getText());
            if(!Objects.equals(nameTextField.getText(), "")){
                PlaylistItemProducer playlistItemProducer = new PlaylistItemProducer();
                Mp3PlayerComponent playlist = playlistItemProducer.createItem();
                playlistItemProducer.getPlaylistItem().setPlaylistSongs(playlistTable.getItems());
                playlistItemProducer.getPlaylistItem().setName(nameTextField.getText());
                Playlists.getInstance().getPlaylists().add(playlistItemProducer.getPlaylistItem());

                ((Stage) createButton.getScene().getWindow()).close();
            }

        });

    }

}
