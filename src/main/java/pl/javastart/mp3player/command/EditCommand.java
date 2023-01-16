package pl.javastart.mp3player.command;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItem;
import pl.javastart.mp3player.mp3.Mp3Song;

import java.io.IOException;

public class EditCommand extends Command<PlaylistItem> {
    public EditCommand(TableView<PlaylistItem> playlistItemTableView, TableView<Mp3Song> contentTableView, Button button) {
        super(playlistItemTableView, contentTableView, button);
    }

    @Override
    public void execute() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playlistCreatorPane.fxml"));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Playlist");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); //ignore
        }
    }
}
