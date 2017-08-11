package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class Controller {

    @FXML
    Button createButton;
    @FXML
    Button addButton;
    @FXML
    ListView notesView;
    @FXML
    TextField titleField;
    @FXML
    TextArea messageArea;
    @FXML
    DatePicker datePicker;

    private String fileName = "notes.txt";
    static ObservableList<Note> notes = FXCollections.observableArrayList();

    public void initialize(){
        initDatePicker();
        initNoteView();
        initList();
        initButtons();

    }

    private void initNoteView(){
        //notes = notesView.getItems();
        notes.add(new Note("Notatka 1","Tresc 1"));
        notes.add(new Note("Notatka 2","Tresc 2"));
        notes.add(new Note("Notatka 3","Tresc 3"));
        FileOperations.loadNotesFromFile(fileName);
        notesView.setItems(notes);
    }

    private void initDatePicker(){
        datePicker.setValue(LocalDate.now());
        datePicker.setDisable(true);
    }

    private void initList(){
        notesView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    datePicker.setDisable(false);

                    Note tmp = (Note)newValue;
                    titleField.setText(tmp.getTitle());
                    messageArea.setText(tmp.getMessage());
                    datePicker.setValue(tmp.getLocalDate());
                    addButton.setText("Save");
                });
    }
    private void initButtons(){
        addButton.setOnAction(event -> {

            if(addButton.getText().equals("Add")){
                notes.add(new Note(titleField.getText(),messageArea.getText()));
                notesView.getSelectionModel().selectLast();
            }
            if(addButton.getText().equals("Save")){
                Note editedNote = (Note)notesView.getSelectionModel().getSelectedItem();
                editedNote.setLocalDate(datePicker.getValue());
                editedNote.setMessage(messageArea.getText());
                editedNote.setTitle(titleField.getText());
                notesView.getItems().set( notesView.getSelectionModel().getSelectedIndex(), editedNote );

            }
        });
        createButton.setOnAction(event -> {
            notesView.getSelectionModel().clearSelection();
            datePicker.setDisable(true);
            addButton.setText("Add");
            titleField.setText("");
            messageArea.setText("");
            datePicker.setValue(LocalDate.now());
        });
    }


}
