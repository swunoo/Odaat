package dev.java.odaat.Entity;

import java.time.LocalDate;

public class Note {
    int id;
    LocalDate createdAt;
    LocalDate updatedAt;
    String title;
    String content;

    public Note(){ /*For Jackson*/ }

    public Note(LocalDate createdAt, LocalDate updatedAt, String title, String content) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.content = content;
    }

    public Note(int id, LocalDate createdAt, LocalDate updatedAt, String title, String content) {
        this(createdAt, updatedAt, title, content);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
