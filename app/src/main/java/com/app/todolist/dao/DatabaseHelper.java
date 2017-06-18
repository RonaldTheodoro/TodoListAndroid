package com.app.todolist.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE = "tasks";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register(" +
            User.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            User.NAME + " TEXT NOT NULL, " +
            User.LOGIN + " TEXT NOT NULL, " +
            User.PASSWORD + " TEXT NOT NULL)"
        );
        db.execSQL("CREATE TABLE task(" +
            Task.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Task.NAME + " TEXT NOT NULL, " +
            Task.CREATION_DATE + " DATE DEFAULT CURRENT_TIMESTAMP, " +
            Task.FINISHED_DATE + " DATETIME)"
        );
        db.execSQL("INSERT INTO " + User.TABLE + "(" +
            User.NAME + ", " +
            User.LOGIN + ", " +
            User.PASSWORD + ") VALUES ('admin', 'admin', 'admin')"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class User {
        public static final String TABLE = "register";
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String[] COLUMNS = new String[] {
            ID, NAME, LOGIN, PASSWORD
        };
    }

    public static class Task {
        public static final String TABLE = "task";
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String CREATION_DATE = "creation_date";
        public static final String FINISHED_DATE = "finished_date";
        public static final String[] COLUMNS = new String[] {
            ID, NAME, CREATION_DATE, FINISHED_DATE
        };
    }
}
