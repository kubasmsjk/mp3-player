package pl.javastart.mp3player.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import pl.javastart.mp3player.command.CreateCommand;
import pl.javastart.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.javastart.mp3player.mp3.Song;


public class PlaylistCreatorController {
    @FXML
    private VBox contentVBox;
    @FXML
    private Label libraryLabel;
    @FXML
    private Label playlistLabel;
    @FXML
    private TableView<Song> libraryTable;
    @FXML
    private TableView<Song> playlistTable;
    @FXML
    private Button createButton;
    @FXML
    private TextField nameTextField;

    private static final String TITLE_COLUMN = "Title";
    private static final String AUTHOR_COLUMN = "Author";
    private static final String ALBUM_COLUMN = "Album";

    //inicjalizacja widoku
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

    //konfiguracja kolumn tabeli
    private void configureTableColumns(TableView<Song> table) {
        TableColumn<Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);

        TableColumn<Song, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setSortable(false);

        TableColumn<Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        albumColumn.setSortable(false);

        table.getColumns().add(titleColumn);
        table.getColumns().add(authorColumn);
        table.getColumns().add(albumColumn);
    }

    //konfiguracja kliknięć tabeli
    private void configureTableClick() {
        libraryTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!libraryTable.getSelectionModel().getSelectedCells().isEmpty()) {
                Song song = libraryTable.getSelectionModel().getSelectedItem();
                playlistTable.getItems().add(song);
                libraryTable.getItems().remove(song);
                createButton.setDisable(false);
            }
        });

        playlistTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!playlistTable.getSelectionModel().getSelectedCells().isEmpty()) {
                Song song = playlistTable.getSelectionModel().getSelectedItem();
                libraryTable.getItems().add(song);
                playlistTable.getItems().remove(song);
                if (playlistTable.getItems().size() == 0) {
                    createButton.setDisable(true);
                }
            }
        });
    }

    //stworzenie playlisty
    private void createPlaylist() {
        createButton.setOnAction(event -> {
            new CreateCommand(playlistTable, nameTextField, createButton).execute();
        });
    }

    public VBox getContentVBox() {
        return contentVBox;
    }

    public TableView<Song> getLibraryTable() {
        return libraryTable;
    }

    public TableView<Song> getPlaylistTable() {
        return playlistTable;
    }

    public Label getLibraryLabel() {
        return libraryLabel;
    }

    public Label getPlaylistLabel() {
        return playlistLabel;
    }

    public Button getCreateButton() {
        return createButton;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }
}
