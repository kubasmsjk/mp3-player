package pl.ztp.mp3player.command;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import pl.ztp.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.ztp.mp3player.factoryMethodComponents.PlaylistItem;
import pl.ztp.mp3player.fileFacade.Song;
import pl.ztp.mp3player.player.Mp3Player;

public class DeleteCommand extends Command<PlaylistItem> {
    public DeleteCommand(TableView<PlaylistItem> playlistItemTableView, TableView<Song> contentTableView, Button button) {
        super(playlistItemTableView, contentTableView, button);
    }

    @Override
    public void execute() {
        PlaylistItem selectedItem = getPlaylistItemTableView().getSelectionModel().getSelectedItem();
        getPlaylistItemTableView().getItems().remove(selectedItem);
        Mp3Player.getInstance().setMp3PlayerComponent(MusicLibraryItem.getInstance());
        getContentTableView().setItems(MusicLibraryItem.getInstance().getLibrarySongs());
        getButton().setDisable(true);
    }
}
