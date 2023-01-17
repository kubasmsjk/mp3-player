package pl.ztp.mp3player.factoryMethodComponents;

import pl.ztp.mp3player.player.Mp3PlayerComponent;

interface IMp3PlayerComponentFactory {
    Mp3PlayerComponent createItem();
}
