package pl.javastart.mp3player.mp3;

import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.util.List;

interface IMp3Parser {
    Mp3Song createMp3Song(File file) throws IOException, TagException;

    List<Mp3Song> createMp3List(File dir) throws IOException, TagException;
}
