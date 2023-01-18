package pl.ztp.mp3player.fileFacade;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class FileMetadataFacade {

    //stworzenie obiektu piosenki
    public Song createSong(File file) {
        String ext = FilenameUtils.getExtension(file.getAbsolutePath());
        if (ext.equals("mp3")) {
            Mp3File mp3File = new Mp3File(file);
            return mp3File.getSongWithMetadata();
        }
        if (ext.equals("wav")) {
            WavFile wavFile = new WavFile(file);
            return wavFile.getSongWithMetadata();
        }
        return null;
    }

}
