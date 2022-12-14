module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jid3lib;
    requires jump3r;
    requires jaudiotagger;
    requires java.desktop;
    requires org.apache.commons.io;

    exports pl.javastart.mp3player.main to javafx.graphics;
    opens pl.javastart.mp3player.controller to javafx.fxml;
    opens pl.javastart.mp3player.mp3 to javafx.base;
}