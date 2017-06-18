package com.app.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.app.todolist.dao.UserDao;
import com.app.todolist.util.Message;

public class LoginActivity extends AppCompatActivity {
    private EditText editUser;
    private EditText editPassword;
    private CheckBox checkboxConnect;
    private UserDao userDao;
    private static final String KEEP_CONNECTED = "keep_connected";
    private static final String PREFERENCE_NAME = "LoginActivityPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUser = (EditText) findViewById(R.id.login_edit_user);
        editPassword = (EditText) findViewById(R.id.login_edit_password);
        checkboxConnect = (CheckBox) findViewById(R.id.login_checkbox_connect);

        userDao = new UserDao(this);

        SharedPreferences preferences = getSharedPreferences(
            PREFERENCE_NAME, MODE_PRIVATE);

        if (preferences.getBoolean(KEEP_CONNECTED, false))
            callMainActivity();
    }

    public void login(View view) {
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();
        boolean validation;

        validation = Message.validateFields(
            user, editUser, getString(R.string.error_name_message));

        validation = Message.validateFields(
            password,
            editPassword,
            getString(R.string.error_password_message)
        );

        if (validation)
            validateLogin(user, password);
    }

    private void validateLogin(String user, String password) {
        if (userDao.validateLogin(user, password)) {
            keepConnected();
            callMainActivity();
        } else
            Message.showToastMessage(
                this, getString(R.string.error_login_message));
    }

    private void callMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void keepConnected() {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        if (checkboxConnect.isChecked()) {
            preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
            editor = preferences.edit();
            editor.putBoolean(KEEP_CONNECTED, true);
            editor.apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
    }
}
