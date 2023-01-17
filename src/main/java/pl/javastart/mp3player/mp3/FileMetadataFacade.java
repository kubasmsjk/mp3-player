package pl.javastart.mp3player.mp3;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class FileMetadataFacade {
    public Mp3Song createSong(File file) {
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

    //@Override
    //public List<Mp3Song> createMp3List(File dir) throws IOException, TagException {
    //    return null;
    //}
}
