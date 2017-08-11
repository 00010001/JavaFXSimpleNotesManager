package sample;

import java.time.LocalDate;

/**
 * Created by RENT on 2017-08-10.
 */
public class Note {
    private String title;
    private String message;
    private LocalDate localDate;


    public Note(String title, String message) {
        this.title = title;
        this.message = message;
        this.localDate = LocalDate.now();
    }
    public Note(String title, String message, LocalDate date) {
        this.title = title;
        this.message = message;
        this.localDate = date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return localDate + "       " + title;
    }
}
