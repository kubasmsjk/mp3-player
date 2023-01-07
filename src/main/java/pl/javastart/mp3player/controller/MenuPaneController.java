package pl.javastart.mp3player.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPaneController {
    @FXML
    private MenuItem fileMenuItem;
    @FXML
    private MenuItem dirMenuItem;
    @FXML
    private MenuItem closeMenuItem;
    @FXML
    private MenuItem createPlaylistMenuItem;
    @FXML
    private MenuItem deletePlaylistMenuItem;
    @FXML
    private Menu libraryMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private Menu sortMenuItem;
    @FXML
    private MenuItem sortByAuthorMenuItem;
    @FXML
    private MenuItem sortByLengthMenuItem;
    @FXML
    private MenuItem sortByTitleMenuItem;

    public MenuItem getFileMenuItem() {
        return fileMenuItem;
    }

    public MenuItem getDirMenuItem() {
        return dirMenuItem;
    }

    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    public MenuItem getCreatePlaylistMenuItem() {
        return createPlaylistMenuItem;
    }

    public MenuItem getDeletePlaylistMenuItem() {
        return deletePlaylistMenuItem;
    }

    public Menu getLibraryMenuItem() {
        return libraryMenuItem;
    }

    public MenuItem getSortByAuthorMenuItem() {
        return sortByAuthorMenuItem;
    }

    public MenuItem getSortByLengthMenuItem() {
        return sortByLengthMenuItem;
    }

    public MenuItem getSortByTitleMenuItem() {
        return sortByTitleMenuItem;
    }

    public Menu getSortMenuItem() {
        return sortMenuItem;
    }

    public void initialize() {
        configureMenu();
    }

    private void configureMenu() {
        closeMenuItem.setOnAction(x -> Platform.exit());

        aboutMenuItem.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/aboutPane.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setTitle("Mp3Player v1.0 - about");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace(); //ignore
                }
            }
        });
    }
}