package com.app.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.app.todolist.dao.UserDao;
import com.app.todolist.model.User;

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

    @Override
    protected void onDestroy() {
        userDao.closeConnection();
        super.onDestroy();
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
