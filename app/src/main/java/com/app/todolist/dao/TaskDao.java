package com.app.todolist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public TaskDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null)
            database = databaseHelper.getWritableDatabase();
        return database;
    }

    private Task createTask(Cursor cursor) {
        Task task = new Task(
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Task.ID)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.Task.NAME)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.Task.CREATION_DATE)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.Task.FINISHED_DATE))
        );
        return task;
    }

    public List<Task> listTask() {
        Cursor cursor = getDatabase().query(
            DatabaseHelper.Task.TABLE,
            DatabaseHelper.Task.COLUMNS,
            null, null, null, null, null
        );

        List<Task> tasks = new ArrayList<Task>();

        while (cursor.moveToNext()) {
            Task task = createTask(cursor);
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

    public long saveTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.Task.NAME, task.getName());

        if (task.getId() != null)
            return getDatabase().update(
                DatabaseHelper.Task.TABLE,
                values,
                "_id = ?",
                new String[] {task.getId().toString()}
            );
        return getDatabase().insert(DatabaseHelper.Task.TABLE, null, values);
    }

    public boolean deleteTask(int id) {
        return getDatabase().delete(
            DatabaseHelper.Task.TABLE,
            "_id = ?",
            new String[] {Integer.toString(id)}
        ) > 0;
    }

    public Task searchTaskById(int id) {
        Cursor cursor = getDatabase().query(
            DatabaseHelper.Task.TABLE,
            DatabaseHelper.Task.COLUMNS,
            "_id = ?",
            new String[] {Integer.toString(id)},
            null, null, null
        );

        if (cursor.moveToNext()) {
            Task task = createTask(cursor);
            cursor.close();
            return task;
        }
        return null;
    }

    public void closeConnection() {
        databaseHelper.close();
        database = null;
    }
}
