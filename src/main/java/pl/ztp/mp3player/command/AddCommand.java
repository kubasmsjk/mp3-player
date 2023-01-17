package pl.ztp.mp3player.command;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.ztp.mp3player.controller.PlaylistCreatorController;
import pl.ztp.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.ztp.mp3player.mp3.Song;

import java.io.IOException;

public class AddCommand extends Command<Song> {
    public AddCommand(TableView<Song> contentTableView) {
        super(contentTableView);
    }

    @Override
    public void execute() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playlistCreatorPane.fxml"));
            Parent root = (Parent) loader.load();
            PlaylistCreatorController playlistCreatorController = loader.getController();
            playlistCreatorController.getContentVBox().getChildren().remove(playlistCreatorController.getCreateButton());
            playlistCreatorController.getContentVBox().getChildren().remove(playlistCreatorController.getNameTextField());
            playlistCreatorController.getPlaylistTable().setItems(getContentTableView().getItems());
            playlistCreatorController.getLibraryTable().setItems(FXCollections.observableArrayList(MusicLibraryItem.getInstance().getLibrarySongs()));
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
