package pl.javastart.mp3player.mp3;

import de.sciss.jump3r.Main;
import org.apache.commons.io.FilenameUtils;
import org.farng.mp3.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.wav.WavTag;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Mp3WavAdapter implements IMp3Parser {
    Mp3File mp3File = new Mp3File();
    public File convertWavFileToMp3File(File source, File target) throws IOException {
        AudioFile f;
        WavTag tag;
        try {
            f = AudioFileIO.read(source);
            tag = (WavTag) f.getTag();
        } catch (CannotReadException | org.jaudiotagger.tag.TagException | ReadOnlyFileException |
                 InvalidAudioFrameException e) {
            throw new RuntimeException(e);
        }

        String[] mp3Args = {"--tt", tag.getFirst(FieldKey.TITLE),
                "--ta", tag.getFirst(FieldKey.ARTIST),
                "--tl", tag.getFirst(FieldKey.ALBUM),
                "--add-id3v2",
                "-q", "0",
                "-m", "s",
                source.getAbsolutePath(),
                target.getAbsolutePath()
        };
        (new Main()).run(mp3Args);
        return target;
    }

    @Override
    public Mp3Song createMp3Song(File file) throws IOException, TagException {
        String ext = FilenameUtils.getExtension(file.getAbsolutePath());
        if (ext.equals("wav")) {
            File target = new File("C:\\Users\\JakubMieczkowski(109\\IdeaProjects\\mp3player\\songs\\a5.mp3");
            file = convertWavFileToMp3File(file, target);
        }
        return mp3File.createMp3Song(file);
    }

    @Override
    public List<Mp3Song> createMp3List(File dir) throws IOException, TagException {
        return null;
    }
}
