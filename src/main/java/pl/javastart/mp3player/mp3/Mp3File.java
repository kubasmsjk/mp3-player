package pl.javastart.mp3player.mp3;

import javafx.scene.media.Media;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mp3File implements IMp3Parser{
    public Mp3Song createMp3Song(File file) throws IOException, TagException {
        MP3File mp3File = new MP3File(file);

        AudioFile f;
        try {
            f = AudioFileIO.read(file);
        } catch (CannotReadException | InvalidAudioFrameException | ReadOnlyFileException |
                 org.jaudiotagger.tag.TagException e) {
            throw new RuntimeException(e);
        }


        String absolutePath = file.getAbsolutePath();
        String title = mp3File.getID3v2Tag().getSongTitle();
        String author = mp3File.getID3v2Tag().getLeadArtist();
        String album = mp3File.getID3v2Tag().getAlbumTitle();
        int duration = f.getAudioHeader().getTrackLength();
        return new Mp3Song(title, author, album, duration, absolutePath);
    }

    public List<Mp3Song> createMp3List(File dir) throws IOException, TagException {
        if(!dir.isDirectory()) {
            throw new IllegalArgumentException("Not a directory");
        }
        List<Mp3Song> songList = new ArrayList<>();
        File[] files = dir.listFiles();
        for(File f: files) {
            String fileExtension = f.getName().substring(f.getName().lastIndexOf(".") + 1);
            if(fileExtension.equals("mp3"))
                songList.add(createMp3Song(f));
        }

        return songList;
    }
}
