package com.example.firstapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    public void setData(ArrayList<Contact> data){this.data = data;}
    private ArrayList<Contact> data;
    private ArrayList<Contact> databackup;
    private Activity context;
    private LayoutInflater inflater;
    public ContactAdapter(){

    }
    public ContactAdapter(ArrayList<Contact> data,Activity activity){
        this.data = data;
        this.context = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.contact_item,null);
        }
        ImageView imageProfile = v.findViewById(R.id.imgItem);
        TextView tvName = v.findViewById(R.id.tvName);
        tvName.setText(data.get(position).getName());
        TextView tvphone = v.findViewById(R.id.tvPhone);
        tvphone.setText(data.get(position).getPhone());
        return v;
    }
}
