package com.app.todolist.model;

public class Task {
    private Integer id;
    private String name;
    private String creation_date;
    private String finished_date;

    public Task() { }

    public Task(Integer id, String name, String creation_date, String finished_date) {
        this.id = id;
        this.name = name;
        this.creation_date = creation_date;
        this.finished_date = finished_date;
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

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getFinished_date() {
        return finished_date;
    }

    public void setFinished_date(String finished_date) {
        this.finished_date = finished_date;
    }
}
