package by.gsu.epamlab.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private LocalDate date;
    private String description;
    private String login;
    private boolean hasFile;

    public Task(int id, LocalDate date, String description, String login, boolean hasFile) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.login = login;
        this.hasFile = hasFile;
    }

    public Task() {
    }

    public Task(String description, String login, boolean hasFile) {
        this.description = description;
        this.login = login;
        this.hasFile = hasFile;
    }

    public Task(String description, String login, boolean hasFile, LocalDate date) {
        this.description = description;
        this.login = login;
        this.hasFile = hasFile;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasFile() {
        return hasFile;
    }

    public void setHasFile(boolean hasFile) {
        this.hasFile = hasFile;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
