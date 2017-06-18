package com.app.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText editUser;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUser = (EditText) findViewById(R.id.login_edit_user);
        editPassword = (EditText) findViewById(R.id.login_edit_password);
    }

    public void login(View view) {
        String user = editUser.getText().toString();
        String password = editPassword.getText().toString();
        boolean validation = true;

        if (user == null || user.equals("")) {
            validation = false;
            editUser.setError(getString(R.string.error_name_message));
        }

        if (password == null || password.equals("")) {
            validation = false;
            editPassword.setError(getString(R.string.error_password_message));
        }

        if (validation) {

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
        if (id == R.id.action_settings)
            return true;
        return super.onOptionsItemSelected(item);
    }
}
