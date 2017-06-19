package com.app.todolist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.todolist.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UserDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDatabase() {
        if (database == null)
            database = databaseHelper.getWritableDatabase();
        return database;
    }

    private User createUser(Cursor cursor) {
        User user = new User(
            cursor.getInt(cursor.getColumnIndex(DatabaseHelper.User.ID)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.User.NAME)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.User.LOGIN)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.User.PASSWORD)),
            cursor.getString(
                cursor.getColumnIndex(DatabaseHelper.User.CREATED_AT))
        );
        return user;
    }

    public List<User> listUsers() {
        Cursor cursor = getDatabase().query(
            DatabaseHelper.User.TABLE,
            DatabaseHelper.User.COLUMNS,
            null, null, null, null, null
        );

        List<User> users = new ArrayList<User>();

        while (cursor.moveToNext()) {
            User user = createUser(cursor);
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public long saveUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.User.NAME, user.getName());
        values.put(DatabaseHelper.User.LOGIN, user.getLogin());
        values.put(DatabaseHelper.User.PASSWORD, user.getPassword());
        values.put(DatabaseHelper.User.CREATED_AT, user.getCreatedAt());

        if (user.getId() != null)
            return getDatabase().update(
                DatabaseHelper.User.TABLE,
                values,
                "_id = ?",
                new String[] {user.getId().toString()}
            );
        return getDatabase().insert(DatabaseHelper.User.TABLE, null, values);
    }

    public boolean deleteUser(int id) {
        return getDatabase().delete(
            DatabaseHelper.User.TABLE,
            "_id = ?",
            new String[] {Integer.toString(id)}
        ) > 0;
    }

    public User searchUserById(int id) {
        Cursor cursor = getDatabase().query(
            DatabaseHelper.User.TABLE,
            DatabaseHelper.User.COLUMNS,
            "_id = ?",
            new String[] {Integer.toString(id)},
            null, null, null
        );

        if (cursor.moveToNext()) {
            User user = createUser(cursor);
            cursor.close();
            return user;
        }
        return null;
    }

    public boolean validateLogin(String user, String password) {
        Cursor cursor = getDatabase().query(
            DatabaseHelper.User.TABLE,
            null,
            "login = ? AND password = ?",
            new String[] {user, password},
            null, null, null
        );
        return (cursor.moveToFirst());
    }

    public void closeConnection() {
        databaseHelper.close();
        database = null;
    }
}
