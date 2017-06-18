package com.app.todolist.util;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

public class Message {

    public static void showToastMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static boolean validateFields(
            String value, EditText editField, String message) {
        if (value == null || value.equals("")) {
            editField.setError(message);
            return false;
        }
        return true;
    }
}
