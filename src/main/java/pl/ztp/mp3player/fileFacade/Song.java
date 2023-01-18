package pl.ztp.mp3player.fileFacade;

public class Song {
    private String title;
    private String author;
    private String album;
    private int duration;
    private String filePath;

    public Song(String title, String author, String album, int duration, String filePath) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.duration = duration;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toString() {
        return "Mp3Song [title=" + title + ", author=" + author + ", album=" + album + "]";
    }

}