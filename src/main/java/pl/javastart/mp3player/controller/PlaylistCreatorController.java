package pl.javastart.mp3player.controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3Player;
import pl.javastart.mp3player.player.Mp3PlayerComponent;
import pl.javastart.mp3player.templates.MusicLibraryItemProducer;
import pl.javastart.mp3player.templates.PlaylistItemProducer;

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
    ObservableList<Mp3Song> playlist;
    static ObservableList<Mp3Song> elo;
    private Mp3Player player = null;

    public void initialize(ObservableList<Mp3Song> songsList) {
        createButton.setDisable(true);
        libraryLabel.setText("Library");
        playlistLabel.setText("Playlist");
        configureTableColumns(libraryTable);
        configureTableColumns(playlistTable);
        this.playlist = FXCollections.observableArrayList(songsList);
        libraryTable.setItems(playlist);
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
    private void createPlaylist(){
        libraryTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Mp3Song selectedIndex = libraryTable.getSelectionModel().getSelectedItem();
                playlistTable.getItems().add(selectedIndex);
                libraryTable.getItems().remove(selectedIndex);
                createButton.setDisable(false);

        });

        playlistTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Mp3Song selectedIndex = playlistTable.getSelectionModel().getSelectedItem();
            libraryTable.getItems().add(selectedIndex);
            playlistTable.getItems().remove(selectedIndex);
            if(playlistTable.getItems().size() == 0 ){
                createButton.setDisable(true);
            }
        });

        createButton.setOnAction(event -> {
            player = Mp3Player.getInstance();
            PlaylistItemProducer playlistItemProducer = new PlaylistItemProducer();
            Mp3PlayerComponent playlist = playlistItemProducer.createItem();
            player.setMp3PlayerComponent(playlist);
            Mp3PlayerComponent.setSongList(playlistTable.getItems());
            ((Stage) createButton.getScene().getWindow()).close();
        });

    }
   public static ObservableList<Mp3Song> get(){
       return elo;
   }
}
