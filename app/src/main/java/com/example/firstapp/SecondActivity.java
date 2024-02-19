package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList<user> lUser = new ArrayList<>();
    RecyclerView rc_user;
    EditText edtName, edtAddress;
    Button btnAdd;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Init();
        UpdateData();
        ShowData();

        btnAdd.setOnClickListener(v -> {
            AddUser();
        });
    }

    private void AddUser() {
        lUser.add(new user(edtName.getText().toString().trim(), edtAddress.getText().toString().trim(), 0));
        userAdapter.notifyDataSetChanged();
    }

    private void Init() {
        rc_user = findViewById(R.id.rc_user);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void ShowData() {
        userAdapter = new UserAdapter(SecondActivity.this, lUser);
        rc_user.setAdapter(userAdapter);
        rc_user.setLayoutManager(new LinearLayoutManager(this));
    }

    private void UpdateData() {
        lUser.add(new user("Chung1", "abc", R.drawable.ic_launcher_background));
        lUser.add(new user("CHung2", "dce", R.drawable.ic_launcher_background));
    }
}