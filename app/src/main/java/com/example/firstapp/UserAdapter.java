package com.example.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context mContext;
    ArrayList<user> lUser;
    TextView name, address;
    ImageView img;
    IclickItem iClickItem;
    interface IclickItem {
        void onClickItem(user user);
    }

    public UserAdapter(Context mContext, ArrayList<user> lUser) {
        this.mContext = mContext;
        this.lUser = lUser;
    }

    public void setData(ArrayList<user> data){
        this.lUser = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            address = itemView.findViewById(R.id.tvAddress);
            img = itemView.findViewById(R.id.imgItem);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View userView = inflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        user user = lUser.get(position);
        name.setText(user.name);
        address.setText(user.address);
        img.setImageResource(user.img);
        holder.itemView.setOnClickListener(v -> {
            iClickItem.onClickItem(user);
        });
    }

    @Override
    public int getItemCount() {
        return lUser.size();
    }
}
