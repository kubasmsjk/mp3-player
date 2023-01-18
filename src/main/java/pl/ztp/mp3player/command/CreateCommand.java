package pl.ztp.mp3player.command;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.ztp.mp3player.factoryMethodComponents.PlaylistItemProducer;
import pl.ztp.mp3player.factoryMethodComponents.Playlists;
import pl.ztp.mp3player.fileFacade.Song;
import pl.ztp.mp3player.player.Mp3PlayerComponent;

import java.util.Objects;

public class CreateCommand extends Command<Song> {

    public CreateCommand(TableView<Song> playlistItemTableView, TextField nameTextField, Button button) {
        super(playlistItemTableView, nameTextField, button);
    }

    @Override
    public void execute() {
        if (Objects.equals(getNameTextField().getText(), "")) {
            getNameTextField().setPromptText("You need to specify the name");
            getNameTextField().setStyle("-fx-prompt-text-fill: red");
        } else {
            PlaylistItemProducer playlistItemProducer = PlaylistItemProducer.getInstance();
            Mp3PlayerComponent playlist = playlistItemProducer.createItem();
            playlistItemProducer.getPlaylistItem().setPlaylistSongs(getPlaylistItemTableView().getItems());
            playlistItemProducer.getPlaylistItem().setName(getNameTextField().getText());
            Playlists.getInstance().getPlaylists().add(playlistItemProducer.getPlaylistItem());
            ((Stage) getButton().getScene().getWindow()).close();
        }
    }
}
