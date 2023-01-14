package pl.javastart.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.javastart.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.javastart.mp3player.factoryMethodComponents.PlaylistItem;
import pl.javastart.mp3player.mp3.Mp3File;
import pl.javastart.mp3player.mp3.Mp3Song;
import pl.javastart.mp3player.mp3.Mp3WavAdapter;
import pl.javastart.mp3player.player.Mp3Player;
import pl.javastart.mp3player.player.Mp3PlayerComponent;
import pl.javastart.mp3player.strategy.ISortStrategy;
import pl.javastart.mp3player.strategy.SortByAuthorStrategy;
import pl.javastart.mp3player.strategy.SortByLengthStrategy;
import pl.javastart.mp3player.strategy.SortByTitleStrategy;
import pl.javastart.mp3player.factoryMethodComponents.MusicLibraryItemProducer;

import java.io.File;
import java.util.Locale;

public class MainController {

    @FXML
    private MenuPaneController menuPaneController;
    @FXML
    private ContentPaneController contentPaneController;
    @FXML
    private TextField messageTextField;
    private Mp3Player player = null;
    private ISortStrategy strategy;

    public void initialize() {
        createPlayer();
        initLibraryAsMp3PlayerComponent();
        configureTableClick();
        configureMenu();
    }

    private void createPlayer() {
        player = Mp3Player.getInstance();
    }

    private void initLibraryAsMp3PlayerComponent() {
        MusicLibraryItemProducer musicLibraryItemProducer = MusicLibraryItemProducer.getInstance();
        Mp3PlayerComponent musicLibrary = musicLibraryItemProducer.createItem();
        player.setMp3PlayerComponent(musicLibrary);
        contentPaneController.getContentTable().setItems(Mp3PlayerComponent.getSongList());
    }

    private void configureTableClick() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2 && ! contentTable.getSelectionModel().getSelectedCells().isEmpty()) {
                int selectedIndex = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(selectedIndex);
            }
        });
    }

    private void playSelectedSong(int selectedIndex) {
        player.getMp3PlayerComponent().loadSong(selectedIndex);
        configureProgressBar();
        configureVolume();
        configureButtons();
        showMessage(Mp3PlayerComponent.getSongInformations(selectedIndex));
        contentPaneController.getControlPaneController().getPlayButton().setSelected(true);
    }

    private void configureProgressBar() {
        Slider progressSlider = contentPaneController.getControlPaneController().getProgressSlider();
        StackPane trackPane = (StackPane) progressSlider.lookup(".track");

        //ustawienie długości suwaka postępu
        player.getMp3PlayerComponent().getMediaPlayer().setOnReady(() -> {
            progressSlider.setMax(player.getMp3PlayerComponent().getLoadedSongLength());
            contentPaneController.getControlPaneController().getEndTimeTextField().setText(getSongTimeInMMSS((int) Math.round(player.getMp3PlayerComponent().getLoadedSongLength())));
        });
        //zmiana czasu w odtwarzaczu automatycznie będzie aktualizowała suwak
        player.getMp3PlayerComponent().getMediaPlayer().currentTimeProperty().addListener((arg, oldVal, newVal) -> {
                    progressSlider.setValue(newVal.toSeconds());
                    contentPaneController.getControlPaneController().getStartTimeTextField().setText(getSongTimeInMMSS((int) Math.round(newVal.toSeconds())));
                    String style = String.format(Locale.US, "-fx-background-color: linear-gradient(to right, #4c9639 %d%%, #7c7c7c %d%%);", (int) ((100 * newVal.toSeconds()) / progressSlider.getMax()), (int) ((100 * newVal.toSeconds()) / progressSlider.getMax()));
                    trackPane.setStyle(style);
                }
        );
        //przesunięcie suwaka spowoduje przewinięcie piosenki do wskazanego miejsca
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (progressSlider.isValueChanging()) {
                player.getMp3PlayerComponent().getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
            }

        });
    }

    private String getSongTimeInMMSS(int loadedSongLength) {
        int MM = (loadedSongLength % 3600) / 60;
        int SS = loadedSongLength % 60;
        return String.format("%02d:%02d", MM, SS);
    }

    private void configureVolume() {
        Slider volumeSlider = contentPaneController.getControlPaneController().getVolumeSlider();
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        player.getMp3PlayerComponent().setVolume(0.5);
        volumeSlider.valueProperty().bindBidirectional(player.getMp3PlayerComponent().getMediaPlayer().volumeProperty());
    }

    private void configureButtons() {
        TableView<Mp3Song> contentTable = contentPaneController.getContentTable();
        TableView<PlaylistItem> playlistItemTableView = contentPaneController.getPlayListPaneController().getContentTable();
        ToggleButton playButton = contentPaneController.getControlPaneController().getPlayButton();


        Button prevButton = contentPaneController.getControlPaneController().getPreviousButton();
        Button nextButton = contentPaneController.getControlPaneController().getNextButton();

        playButton.setDisable(false);
        prevButton.setDisable(false);
        nextButton.setDisable(false);

        playButton.setOnAction(event -> {
            if (playButton.isSelected()) {
                player.getMp3PlayerComponent().play();
                showMessage(Mp3PlayerComponent.getSongInformations(contentTable.getSelectionModel().getSelectedIndex()));
            } else {
                player.getMp3PlayerComponent().stop();
                showMessage("Song paused");
            }
        });

        nextButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() + 1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });

        prevButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() - 1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });

        playlistItemTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (!contentTable.getSelectionModel().getSelectedCells().isEmpty()) {

                    PlaylistItem playlistItem = playlistItemTableView.getSelectionModel().getSelectedItem();
                    contentPaneController.getContentTable().setItems(playlistItem.getPlaylistSongs());
                    player.setMp3PlayerComponent(playlistItem);

                }
            });

    }

    private void configureMenu() {
        MenuItem openFile = menuPaneController.getFileMenuItem();
        MenuItem openDir = menuPaneController.getDirMenuItem();
        MenuItem musicLibrary = menuPaneController.getLibraryMenuItem();
        Menu sortMenuItem = menuPaneController.getSortMenuItem();

        //USUNAC PRZY ADAPTERZE
        Mp3File mp3File = new Mp3File();

        openFile.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3", "*.mp3", "*.wav"));
            File file = fc.showOpenDialog(new Stage());
            try {
                Mp3WavAdapter mp3WavAdapter = new Mp3WavAdapter();
                Mp3Song mp3Song = mp3WavAdapter.createMp3Song(file);
                MusicLibraryItem.getSongList().add(mp3Song);
                showMessage("Zaladowano plik " + file.getName());
            } catch (Exception e) {
                showMessage("Nie można otworzyc pliku " + file.getName());
                System.out.println(e.getMessage());
            }
        });

        openDir.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            File dir = dc.showDialog(new Stage());
            try {
                contentPaneController.getContentTable().getItems().addAll(mp3File.createMp3List(dir));
                showMessage("Wczytano dane z folderu " + dir.getName());
            } catch (Exception e) {
                showMessage("Wystąpil blad podczas odczytu folderu");
            }
        });

        sortMenuItem.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            String id = menuItem.getId();

            switch (id) {
                case "sortByTitleMenuItem":
                    strategy = new SortByTitleStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                    break;
                case "sortByAuthorMenuItem":
                    strategy = new SortByAuthorStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                    break;
                case "sortByLengthMenuItem":
                    strategy = new SortByLengthStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                    break;
                default:
                    strategy = new SortByTitleStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                    break;
            }
        });

        musicLibrary.setOnAction(event -> {

        });


    }

    private void showMessage(String message) {
        messageTextField.setText(message);
    }

}