package com.app.todolist.model;

public class Task {
    private Integer id;
    private String name;
    private String creationDate;
    private String finishedDate;

    public Task() { }

    public Task(Integer id, String name, String creationDate, String finishedDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.finishedDate = finishedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }
}
