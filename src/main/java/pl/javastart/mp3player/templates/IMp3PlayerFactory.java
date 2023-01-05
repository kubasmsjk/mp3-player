package pl.javastart.mp3player.templates;

import pl.javastart.mp3player.player.Mp3Player;
import pl.javastart.mp3player.player.Mp3PlayerComponent;

public interface IMp3PlayerFactory {
    Mp3PlayerComponent createItem();
}
