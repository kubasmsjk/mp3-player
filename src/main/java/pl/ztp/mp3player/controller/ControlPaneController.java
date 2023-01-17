package pl.ztp.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class ControlPaneController {
    @FXML
    private Button previousButton;

    @FXML
    private ToggleButton playButton;

    @FXML
    private Button nextButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressSlider;
    @FXML
    private ImageView soundImageView;
    @FXML
    private Text startTimeTextField;
    @FXML
    private Text endTimeTextField;

    public Button getPreviousButton() {
        return previousButton;
    }

    public ToggleButton getPlayButton() {
        return playButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public Slider getProgressSlider() {
        return progressSlider;
    }

    public Text getStartTimeTextField() {
        return startTimeTextField;
    }

    public Text getEndTimeTextField() {
        return endTimeTextField;
    }

    //inicjalizacja widoku
    public void initialize() {
        previousButton.setDisable(true);
        playButton.setDisable(true);
        nextButton.setDisable(true);
    }
}