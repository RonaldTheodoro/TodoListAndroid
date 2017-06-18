package com.app.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.todolist.dao.UserDao;

public class LoginActivity extends AppCompatActivity {
    private EditText editUser;
    private EditText editPassword;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUser = (EditText) findViewById(R.id.login_edit_user);
        editPassword = (EditText) findViewById(R.id.login_edit_password);
        userDao = new UserDao(this);
    }

    public void login(View view) {
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();
        boolean validation;

        validation = validateFields(
            user, editUser, getString(R.string.error_name_message));

        validation = validateFields(
            password,
            editPassword,
            getString(R.string.error_password_message)
        );

        if (validation)
            validateLogin(user, password);
    }

    private boolean validateFields(
            String value, EditText editField, String message) {
        if (value == null || value.equals("")) {
            editField.setError(message);
            return false;
        }
        return true;
    }

    private void validateLogin(String user, String password) {
        if (userDao.validateLogin(user, password)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else
            showToastMessage("Usuario invalido");
    }

    private void showToastMessage(String message) {
        Toast.makeText(
            getApplicationContext(),
            message,
            Toast.LENGTH_SHORT
        ).show();
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
