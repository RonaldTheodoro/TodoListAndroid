package com.app.todolist.util;

import android.app.Activity;
import android.widget.Toast;

public class Message {

    public static void showToastMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
}
