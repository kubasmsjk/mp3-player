package pl.javastart.mp3player.controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import pl.javastart.mp3player.mp3.Mp3Song;

import java.io.IOException;

public class PlaylistCreatorController {
    @FXML
    private ContentPaneController contentPaneController;
    @FXML
    private Button createButton;
    @FXML
    private TextField nameTextField;

    public void initialize(ObservableList<Mp3Song> elo) {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        contentTable.setItems(elo);
        //contentPaneController.getContentTable().setItems(elo);
        createButton.setDisable(true);
    }

}
