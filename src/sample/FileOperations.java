package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-08-10.
 */
public class FileOperations {
    public static void loadNotesFromFile(String fileName) {
        Path filePath = Paths.get(fileName);
        List<Note> loadedNotes = new ArrayList<>();
        try {
            loadedNotes = Files.lines(filePath).map(l -> createNoteFromFileline(l))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error while reading from file");
        }
        System.out.println(loadedNotes);
        Controller.notes.addAll(loadedNotes);
    }

    public static void saveToFile(String fileName) {

        List<String> fileLines = Controller.notes.stream()
                .map(note -> createFileLineFromNote(note))
                .collect(Collectors.toList());
        try {
            Files.write(Paths.get(fileName), fileLines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error while saving to file");
        }

    }

    private static Note createNoteFromFileline(String fileLine) {
        String[] lineValues = fileLine.split(",");

        String title = lineValues[0];
        String message = lineValues[1];
        LocalDate date = LocalDate.parse(lineValues[2]);
        return new Note(title, message, date);
    }

    private static String createFileLineFromNote(Note note) {
        return note.getTitle() + "," +
                note.getMessage() + "," +
                note.getLocalDate();
    }


}
