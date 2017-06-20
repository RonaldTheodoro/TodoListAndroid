package com.app.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.app.todolist.adapter.UserAdapter;
import com.app.todolist.dao.UserDao;
import com.app.todolist.model.User;

import java.util.List;

public class ListUsersActivity
        extends AppCompatActivity
        implements OnItemClickListener, OnClickListener {
    private ListView listView;
    private List<User> users;
    private UserAdapter userAdapter;
    private UserDao userDao;
    private int itemPosition;
    private AlertDialog alertDialog;
    private AlertDialog alertConfirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        userDao = new UserDao(this);
        users = userDao.listUsers();
        userAdapter = new UserAdapter(this, users);

        listView = (ListView) findViewById(R.id.list_view_users);
        listView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_register_user)
            startActivity(new Intent(this, UserActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
