package com.example.firstapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter implements Filterable {
    public void setData(ArrayList<Contact> data){this.data = data;}
    private ArrayList<Contact> data;
    private ArrayList<Contact> dataBackup;
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

    @Override
    public Filter getFilter() {
        Filter f = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults fr = new FilterResults();
                if(dataBackup == null)
                    dataBackup = new ArrayList<>(data);

                if(constraint == null || constraint.length() == 0){
                    fr.count = dataBackup.size();
                    fr.values = dataBackup;
                }
                else {
                    ArrayList<Contact> newData = new ArrayList<>();
                    for(Contact c : dataBackup){
                        if(c.getName().toLowerCase().contains(
                                constraint.toString().toLowerCase())){
                            newData.add(c);
                        }
                    }
                    fr.count = newData.size();
                    fr.values = newData;
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = new ArrayList<>();
                ArrayList<Contact> tmp = (ArrayList<Contact>) results.values;
                for(Contact c : tmp){
                    data.add(c);
                }
                notifyDataSetChanged();
            }
        };
        return f;
    }
}
