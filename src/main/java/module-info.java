module mp3player {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jid3lib;
    requires jaudiotagger;
    requires java.desktop;
    requires org.apache.commons.io;
    requires json.simple;

    exports pl.ztp.mp3player.main to javafx.graphics;
    opens pl.ztp.mp3player.controller to javafx.fxml;
    opens pl.ztp.mp3player.mp3 to javafx.base;
    opens pl.ztp.mp3player.factoryMethodComponents to javafx.base;
}