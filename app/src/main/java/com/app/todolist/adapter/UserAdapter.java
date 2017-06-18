package com.app.todolist.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.todolist.R;
import com.app.todolist.model.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        User user = users.get(position);
        if (view == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.users, null);
        }

        TextView textName = (TextView) view.findViewById(R.id.user_list_name);
        textName.setText(user.getName());
        return view;
    }
}
