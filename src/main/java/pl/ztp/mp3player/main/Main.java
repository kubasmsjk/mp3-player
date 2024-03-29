package pl.ztp.mp3player.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //ustawienie widoku odtwarzacza i uruchomienie programu
    @Override
    public void start(Stage stage) throws Exception {
        Pane mainPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/mainPane.fxml")));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setTitle("Mp3 Player");
        stage.show();

        stage.setOnCloseRequest(x -> Platform.exit());
    }
}