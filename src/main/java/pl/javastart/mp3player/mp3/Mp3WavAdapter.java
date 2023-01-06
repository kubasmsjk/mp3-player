package pl.javastart.mp3player.mp3;

import de.sciss.jump3r.Main;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Mp3WavAdapter implements IMp3Parser{

    public File convertWavFileToMp3File(File source,File target) throws IOException {
        String[] mp3Args = { "--preset","standard",
                "-q","0",
                "-m","s",
                source.getAbsolutePath(),
                target.getAbsolutePath()
        };
        (new Main()).run(mp3Args);
        return target;
    }

    @Override
    public Mp3Song createMp3Song(File file) throws IOException, TagException {
        return null;
    }

    @Override
    public List<Mp3Song> createMp3List(File dir) throws IOException, TagException {
        return null;
    }
}
