package com.app.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.app.todolist.dao.UserDao;
import com.app.todolist.model.User;
import com.app.todolist.util.Message;

public class UserActivity extends AppCompatActivity {
    private EditText editUser;
    private EditText editLogin;
    private EditText editPassword;
    private UserDao userDao;
    private User user;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userDao = new UserDao(this);

        editUser = (EditText) findViewById(R.id.user_name);
        editLogin = (EditText) findViewById(R.id.user_login);
        editPassword = (EditText) findViewById(R.id.user_password);
    }

    private void register() {
        boolean validation = true;

        String name = editUser.getText().toString();
        String login = editLogin.getText().toString();
        String password = editPassword.getText().toString();

        validation = Message.validateFields(
            name, editUser, getString(R.string.requested_field));
        validation = Message.validateFields(
            login, editLogin, getString(R.string.requested_field));
        validation = Message.validateFields(
            password, editPassword, getString(R.string.requested_field));

        if (validation)
            createUser(name, login, password);
    }

    private void createUser(String name, String login, String password) {
        user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);

        if (idUser > 0)
            user.setId(idUser);

        saveUser();
    }

    private void saveUser() {
        long id = userDao.saveUser(user);
        if (id != -1)
            showMessageAndOpenMainActivity();
        else
            Message.showToastMessage(this, getString(R.string.user_error));
    }

    private void showMessageAndOpenMainActivity() {
        if (idUser > 0)
            Message.showToastMessage(this, getString(R.string.user_updated));
        else
            Message.showToastMessage(this, getString(R.string.user_saved));

        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        userDao.closeConnection();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);

        if (idUser > 0)
            menu.findItem(R.id.action_delete).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_save:
                this.register();
                break;

            case R.id.action_exit:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
