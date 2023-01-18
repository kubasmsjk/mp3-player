package pl.ztp.mp3player.fileFacade;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.wav.WavTag;

import java.io.File;
import java.io.IOException;

public class WavFile {
    File file;

    public WavFile(File file) {
        this.file = file;
    }

    //pobranie metadanych pliku
    public Song getSongWithMetadata() {
        AudioFile f;
        WavTag tag;
        int duration;

        try {
            f = AudioFileIO.read(file);
            duration = f.getAudioHeader().getTrackLength();
            tag = (WavTag) f.getTag();
        } catch (CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException |
                 IOException e) {
            throw new RuntimeException(e);
        }

        return new Song(tag.getFirst(FieldKey.TITLE), tag.getFirst(FieldKey.ARTIST), tag.getFirst(FieldKey.ALBUM), duration, file.getAbsolutePath());
    }
}

