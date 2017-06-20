package com.app.todolist.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class Message {

    public static void showToastMessage(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }

    public static void addMessageOK(
            Activity activity, String title, String message, int icon) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setNeutralButton("OK", null);
        alert.setIcon(icon);
        alert.show();
    }

    public static void confirmMessage(
            Activity activity,
            String title,
            String message,
            int icon,
            DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Sim", listener);
        alert.setNegativeButton("Não", null);
        alert.setIcon(icon);
        alert.show();
    }

    public static AlertDialog createAlertDialog(Activity activity) {
        final CharSequence[] items = {"Edit", "Delete"};
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Option");
        alert.setItems(items, (DialogInterface.OnClickListener) activity);
        return alert.create();
    }

    public static AlertDialog createDialogConfirm(Activity activity) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setMessage("Do you really want to delete?");
        alert.setPositiveButton(
            "Sim", (DialogInterface.OnClickListener) activity);
        alert.setNegativeButton(
            "Não", (DialogInterface.OnClickListener) activity);
        return alert.create();
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
