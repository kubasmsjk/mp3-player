package pl.ztp.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.ztp.mp3player.command.DeleteCommand;
import pl.ztp.mp3player.command.EditCommand;
import pl.ztp.mp3player.factoryMethodComponents.MusicLibraryItem;
import pl.ztp.mp3player.factoryMethodComponents.PlaylistItem;
import pl.ztp.mp3player.jsonExport.ExportJSON;
import pl.ztp.mp3player.mp3.Song;
import pl.ztp.mp3player.mp3.FileMetadataFacade;
import pl.ztp.mp3player.player.Mp3Player;
import pl.ztp.mp3player.player.Mp3PlayerComponent;
import pl.ztp.mp3player.strategy.ISortStrategy;
import pl.ztp.mp3player.strategy.SortByAuthorStrategy;
import pl.ztp.mp3player.strategy.SortByLengthStrategy;
import pl.ztp.mp3player.strategy.SortByTitleStrategy;
import pl.ztp.mp3player.factoryMethodComponents.MusicLibraryItemProducer;

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

    //zainicjalizowanie widoku
    public void initialize() {
        createPlayer();
        initLibraryAsMp3PlayerComponent();
        configureTableClick();
        configureMenu();
        configurePlaylistButtons();
    }

    //Stworzenie instacji odtwarzacza (Singleton)
    private void createPlayer() {
        player = Mp3Player.getInstance();
    }

    //stworzenie biblioteki (Metoda Fabrykująca) i podpięcie listy piosenek pod tablicę
    private void initLibraryAsMp3PlayerComponent() {
        MusicLibraryItemProducer musicLibraryItemProducer = MusicLibraryItemProducer.getInstance();
        Mp3PlayerComponent musicLibrary = musicLibraryItemProducer.createItem();
        player.setMp3PlayerComponent(musicLibrary);
        contentPaneController.getContentTable().setItems(Mp3PlayerComponent.getSongList());
    }

    //konfiguracja kliknieć w wiersze tabeli
    private void configureTableClick() {
        TableView<Song> contentTable = contentPaneController.getContentTable();
        TableView<PlaylistItem> playlistItemTable = contentPaneController.getPlayListPaneController().getContentTable();

        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2 && !contentTable.getSelectionModel().getSelectedCells().isEmpty()) {
                int selectedIndex = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(selectedIndex);
            }
        });

        playlistItemTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!playlistItemTable.getSelectionModel().getSelectedCells().isEmpty()) {
                defaultControls();
                PlaylistItem playlistItem = playlistItemTable.getSelectionModel().getSelectedItem();
                player.setMp3PlayerComponent(playlistItem);
                Mp3PlayerComponent.setSongList(playlistItem.getPlaylistSongs());
                contentPaneController.getContentTable().setItems(playlistItem.getPlaylistSongs());
            }
        });

    }

    //metoda ustawiająca kontrolki odtwarzacza i wywołująca granie piosenki przez odwarzacz
    private void playSelectedSong(int selectedIndex) {
        player.getMp3PlayerComponent().loadSong(selectedIndex);
        configureProgressBar();
        configureVolume();
        configureButtons();
        showMessage(Mp3PlayerComponent.getSongInformations(selectedIndex));
        contentPaneController.getControlPaneController().getPlayButton().setSelected(true);
    }

    //konfiguracja pasku postępu piosenki
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

    //obliczenie minut i sekund piosenki
    private String getSongTimeInMMSS(int loadedSongLength) {
        int MM = (loadedSongLength % 3600) / 60;
        int SS = loadedSongLength % 60;
        return String.format("%02d:%02d", MM, SS);
    }

    //konfiguracja głosności
    private void configureVolume() {
        Slider volumeSlider = contentPaneController.getControlPaneController().getVolumeSlider();
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        player.getMp3PlayerComponent().setVolume(0.5);
        volumeSlider.valueProperty().bindBidirectional(player.getMp3PlayerComponent().getMediaPlayer().volumeProperty());
    }

    //konfiguracja przycisków odtwarzacza
    private void configureButtons() {
        TableView<Song> contentTable = contentPaneController.getContentTable();
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

    }

    //konfiguracja paska menu
    private void configureMenu() {
        TableView<PlaylistItem> playlistTable = contentPaneController.getPlayListPaneController().getContentTable();
        MenuItem openFile = menuPaneController.getFileMenuItem();
        MenuItem jsonExportMenuItem = menuPaneController.getjsonExportMenuItem();
        MenuItem musicLibrary = menuPaneController.getLibraryMenuItem();
        Menu sortMenuItem = menuPaneController.getSortMenuItem();

        //dodanie pliku muzycznego
        openFile.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3", "*.mp3", "*.wav"));
            File file = fc.showOpenDialog(new Stage());
            try {
                FileMetadataFacade metadataFacade = new FileMetadataFacade();
                Song song = metadataFacade.createSong(file);
                MusicLibraryItem.getSongList().add(song);
                showMessage("Zaladowano plik " + file.getName());
            } catch (Exception e) {
                showMessage("Nie można otworzyc pliku ");

            }
        });

        //export playlist do json
        jsonExportMenuItem.setOnAction(event -> {
            ExportJSON.exportJSON(playlistTable.getItems());
        });

        //sortowanie utworów biblioteki
        sortMenuItem.setOnAction(event -> {
            MenuItem menuItem = (MenuItem) event.getTarget();
            String id = menuItem.getId();

            switch (id) {
                case "sortByTitleMenuItem" -> {
                    strategy = new SortByTitleStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                }
                case "sortByAuthorMenuItem" -> {
                    strategy = new SortByAuthorStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                }
                case "sortByLengthMenuItem" -> {
                    strategy = new SortByLengthStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                }
                default -> {
                    strategy = new SortByTitleStrategy();
                    strategy.sort(Mp3PlayerComponent.getSongList());
                }
            }
        });

        //powrót do biblioteki
        musicLibrary.setOnAction(event -> {
            defaultControls();
            initLibraryAsMp3PlayerComponent();
        });
    }

    //konfiguracja przycisków playlisty
    private void configurePlaylistButtons() {
        TableView<Song> contentTable = contentPaneController.getContentTable();
        TableView<PlaylistItem> playlistTable = contentPaneController.getPlayListPaneController().getContentTable();
        Button delete = contentPaneController.getPlayListPaneController().getPlaylistToolBarPaneController().getDeleteButton();
        Button edit = contentPaneController.getPlayListPaneController().getPlaylistToolBarPaneController().getEditButton();

        delete.setOnAction(event -> {
            if (!playlistTable.getSelectionModel().isEmpty()) {
                new DeleteCommand(playlistTable, contentTable, delete).execute();
                edit.setDisable(true);
            }
        });

        edit.setOnAction(event -> {
            if (!playlistTable.getSelectionModel().isEmpty()) {
                new EditCommand(playlistTable, contentTable, edit).execute();
            }
        });
    }

    //ustawia przyciski oraz stopuje piosenke przy przejsciu do innej listy
    private void defaultControls() {

        if (player.getMp3PlayerComponent().getMediaPlayer() != null) {
            player.getMp3PlayerComponent().getMediaPlayer().stop();
            showMessage("Mp3Player v1.0");
            ToggleButton playButton = contentPaneController.getControlPaneController().getPlayButton();
            Button prevButton = contentPaneController.getControlPaneController().getPreviousButton();
            Button nextButton = contentPaneController.getControlPaneController().getNextButton();

            playButton.setSelected(false);
            playButton.setDisable(true);
            prevButton.setDisable(true);
            nextButton.setDisable(true);
        }
    }

    //pokazuje wiadomość w oknie na dole odtwarzacza
    private void showMessage(String message) {
        messageTextField.setText(message);
    }

}