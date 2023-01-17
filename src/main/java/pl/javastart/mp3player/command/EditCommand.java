package pl.javastart.mp3player.command;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.javastart.mp3player.controller.EditPlaylistPaneController;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItem;
import pl.javastart.mp3player.mp3.Song;

import java.io.IOException;

public class EditCommand extends Command<PlaylistItem> {
    public EditCommand(TableView<PlaylistItem> playlistItemTableView, TableView<Song> contentTableView, Button button) {
        super(playlistItemTableView, contentTableView, button);
    }

    @Override
    public void execute() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editPlaylistPane.fxml"));
            Parent root = (Parent) loader.load();
            EditPlaylistPaneController editPlaylistPaneController = loader.getController();
            PlaylistItem selectedItem = getPlaylistItemTableView().getSelectionModel().getSelectedItem();
            editPlaylistPaneController.getContentTable().setItems(selectedItem.getPlaylistSongs());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Playlist");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); //ignore
        }
    }
}
