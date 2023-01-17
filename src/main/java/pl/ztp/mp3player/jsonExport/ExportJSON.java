package pl.ztp.mp3player.jsonExport;

import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.ztp.mp3player.factoryMethodComponents.PlaylistItem;
import pl.ztp.mp3player.mp3.Song;

public class ExportJSON {
    private static FileWriter file;

    @SuppressWarnings("unchecked")
    public static void exportJSON(ObservableList<PlaylistItem> items) {

        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        ObservableList<Song> listOfSongs;
        JSONObject JSONObjectToWrite = new JSONObject();

        for (PlaylistItem playlistItem : items) {
            JSONArray array = new JSONArray();
            listOfSongs = playlistItem.getPlaylistSongs();

            for (Song mp3Song : listOfSongs) {
                JSONObject playlistContext = new JSONObject();
                playlistContext.put("FilePath", mp3Song.getFilePath());
                playlistContext.put("Duration", mp3Song.getDuration());
                playlistContext.put("Album", mp3Song.getAlbum());
                playlistContext.put("Author", mp3Song.getAuthor());
                playlistContext.put("Name", mp3Song.getTitle());

                array.add(playlistContext);
            }

            JSONObjectToWrite.put(playlistItem.getName(), array);
        }

        try {
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("playlist" + ".json");
            file.write(JSONObjectToWrite.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}





