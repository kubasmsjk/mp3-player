package pl.javastart.mp3player.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.javastart.mp3player.templates.MusicLibraryItem;

import java.io.IOException;

public class playlistToolBar {

    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;

    public void initialize() {

        addButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/playlistCreatorPane.fxml"));
                    Parent root = (Parent) loader.load();
                    PlaylistCreatorController playlistCreatorController = loader.getController();
                    playlistCreatorController.initialize(MusicLibraryItem.getSongList());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setTitle("Playlist");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace(); //ignore
                }
            }
        });
    }
}
