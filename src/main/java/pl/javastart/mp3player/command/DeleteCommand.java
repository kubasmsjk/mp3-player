package pl.javastart.mp3player.command;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import pl.javastart.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItem;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.player.Mp3Player;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public class DeleteCommand extends Command<PlaylistItem> {
    public DeleteCommand(TableView<PlaylistItem> playlistItemTableView, TableView<Mp3Song> contentTableView, Button button) {
        super(playlistItemTableView, contentTableView, button);
    }

    @Override
    public void execute() {
        PlaylistItem selectedItem = getPlaylistItemTableView().getSelectionModel().getSelectedItem();
        getPlaylistItemTableView().getItems().remove(selectedItem);
        Mp3Player.getInstance().setMp3PlayerComponent(MusicLibraryItem.getInstance());
        getContentTableView().setItems(Mp3PlayerComponent.getSongList());
        if (getPlaylistItemTableView().getSelectionModel().isEmpty()) {
            getButton().setDisable(true);
        }
    }
}
