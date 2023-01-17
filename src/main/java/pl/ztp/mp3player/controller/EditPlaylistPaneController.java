package pl.ztp.mp3player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import pl.ztp.mp3player.command.AddCommand;
import pl.ztp.mp3player.mp3.Song;

public class EditPlaylistPaneController {
    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Song> contentTable;

    private static final String TITLE_COLUMN = "Title";
    private static final String AUTHOR_COLUMN = "Author";
    private static final String ALBUM_COLUMN = "Album";
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    //inicjalizacja widoku
    public void initialize() {
        configureTableColumns();
        configureTable();
        configureButtons();
        configureDragAndDrop();
    }

    //konfiguracja tabeli
    private void configureTable() {
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            deleteButton.setDisable(contentTable.getSelectionModel().getSelectedCells().isEmpty());
        });
    }

    //konfiguracja kolumn tabeli
    private void configureTableColumns() {
        TableColumn<Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);

        TableColumn<Song, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setSortable(false);

        TableColumn<Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        albumColumn.setSortable(false);

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
        contentTable.getColumns().add(albumColumn);
    }

    //konfiguracja przycisków
    private void configureButtons() {
        deleteButton.setDisable(true);

        deleteButton.setOnAction(event -> {
            Song song = contentTable.getSelectionModel().getSelectedItem();
            contentTable.getItems().remove(song);
        });

        addButton.setOnAction(event -> {
            new AddCommand(contentTable).execute();
        });
    }

    //konfiguracja drag and drop
    private void configureDragAndDrop() {
        contentTable.setRowFactory(tv -> {
            TableRow<Song> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer) db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Song draggedSong = contentTable.getItems().remove(draggedIndex);

                    int dropIndex;

                    if (row.isEmpty()) {
                        dropIndex = contentTable.getItems().size();
                    } else {
                        dropIndex = row.getIndex();
                    }

                    contentTable.getItems().add(dropIndex, draggedSong);

                    event.setDropCompleted(true);
                    contentTable.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row;
        });

    }

    public TableView<Song> getContentTable() {
        return contentTable;
    }

}
