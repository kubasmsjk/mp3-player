package pl.javastart.mp3player.command;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.javastart.mp3player.mp3.Song;

public abstract class Command<T> implements IMp3Command<T> {
    private TableView<T> playlistItemTableView;
    private TableView<Song> contentTableView;
    private TextField nameTextField;
    private Button button;

    public Command(TableView<Song> contentTableView) {
        this.contentTableView = contentTableView;
    }

    public Command(TableView<T> playlistItemTableView, TextField nameTextField, Button button) {
        this.playlistItemTableView = playlistItemTableView;
        this.nameTextField = nameTextField;
        this.button = button;
    }

    public Command(TableView<T> playlistItemTableView, TableView<Song> contentTableView, Button button) {
        this.playlistItemTableView = playlistItemTableView;
        this.contentTableView = contentTableView;
        this.button = button;
    }

    public TableView<T> getPlaylistItemTableView() {
        return playlistItemTableView;
    }

    public TableView<Song> getContentTableView() {
        return contentTableView;
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public Button getButton() {
        return button;
    }


}
