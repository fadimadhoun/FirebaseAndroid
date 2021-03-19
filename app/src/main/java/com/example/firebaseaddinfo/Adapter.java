package com.example.firebaseaddinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    Context context;
    List<User> usersList;
    public Adapter(Context context , List<User> usersList){
        this.context = context;
        this.usersList = usersList;
    }
    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item , null);

            ViewHolder vh = new ViewHolder();
            vh.name = convertView.findViewById(R.id.UserName);
            vh.number = convertView.findViewById(R.id.UserNumber);
            vh.address = convertView.findViewById(R.id.UserAddress);
            convertView.setTag(vh);

        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.name.setText(usersList.get(position).getName());
        vh.number.setText(usersList.get(position).getName());
        vh.address.setText(usersList.get(position).getName());

        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView number;
        TextView address;


    }
}
