package com.example.firstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton fab;
    ContactAdapter contactAdapter;
    ArrayList<Contact> listContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        UpdateData();
        Intent intent = new Intent(this, SubActivity.class);
        fab.setOnClickListener(v->{
            startActivityForResult(intent, 100);
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("id");
        String name = b.getString("name");
        String phone = b.getString("phone");

        Contact contact = new Contact(id, name, phone);
        if(requestCode == 100 && resultCode == 150){
            listContact.add(contact);
            contactAdapter.notifyDataSetChanged();
        }
    }

    private void UpdateData() {
        listContact.add(new Contact(1, "Chung", "011"));
    }

    private void Init() {
        listView = findViewById(R.id.listView);
        fab = findViewById(R.id.fab);

        contactAdapter = new ContactAdapter(listContact, this);
        contactAdapter.notifyDataSetChanged();
        listView.setAdapter(contactAdapter);
    }
}