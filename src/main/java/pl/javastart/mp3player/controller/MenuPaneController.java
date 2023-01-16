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
import java.util.Objects;

public class MenuPaneController {
    @FXML
    private MenuItem fileMenuItem, dirMenuItem, closeMenuItem, libraryMenuItem, aboutMenuItem;
    @FXML
    private Menu libraryMenu;
    @FXML
    private Menu sortMenuItem;
    @FXML
    private MenuItem sortByAuthorMenuItem, sortByLengthMenuItem, sortByTitleMenuItem;

    //inicjalizacja widoku
    public void initialize() {
        configureMenu();
    }

    //konfiguracja menu
    private void configureMenu() {

        aboutMenuItem.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/aboutPane.fxml")));
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

        closeMenuItem.setOnAction(x -> Platform.exit());
    }

    public MenuItem getFileMenuItem() {
        return fileMenuItem;
    }

    public MenuItem getDirMenuItem() {
        return dirMenuItem;
    }

    public Menu getLibraryMenu() {
        return libraryMenu;
    }

    public MenuItem getLibraryMenuItem() {
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
}