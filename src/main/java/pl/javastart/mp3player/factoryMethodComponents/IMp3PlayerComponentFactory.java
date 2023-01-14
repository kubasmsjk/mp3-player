package pl.javastart.mp3player.factoryMethodComponents;

import pl.javastart.mp3player.player.Mp3PlayerComponent;

interface IMp3PlayerComponentFactory {
    Mp3PlayerComponent createItem();
}
