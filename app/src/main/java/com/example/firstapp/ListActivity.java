package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    EditText edtName;
    FloatingActionButton fabAdd;
    ListView listViewStudent;
    ArrayAdapter adapter;
    ArrayList<String> listName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        Init();
        UpdateData();
        fabAdd.setOnClickListener(v -> {
            listName.add(edtName.getText().toString().trim());
            edtName.setText("");
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        });

        listViewStudent.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(ListActivity.this, "" + listName.get(position).toString(), Toast.LENGTH_SHORT).show();
            listName.remove(position);
            adapter.notifyDataSetChanged();
        });
    }

    private void UpdateData() {
        listName.add("Chung1");
        listName.add("Chung2");
        listName.add("Chung3");
        
        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listName);
        listViewStudent.setAdapter(adapter);
    }

    private void Init() {
        edtName = findViewById(R.id.edtName);
        fabAdd = findViewById(R.id.fabAdd);
        listViewStudent = findViewById(R.id.listViewStudent);
    }
}