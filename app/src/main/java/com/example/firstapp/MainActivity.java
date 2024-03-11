package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton fab;
    ContactAdapter contactAdapter;
    ArrayList<Contact> listContact = new ArrayList<>();

    private int selectedItemId;
    private MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

//        UpdateData();
        setResult(150);
        Intent intent = new Intent(this, SubActivity.class);
        fab.setOnClickListener(v->{
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        int id = b.getInt("id");
        String name = b.getString("name");
        String phone = b.getString("phone");

        if(requestCode == 100 && resultCode == 150){
            Contact contact = new Contact(id, name, phone);
            listContact.add(contact);
        }

        if (requestCode == 110 && resultCode == 150){
            for (Contact c: listContact) {
                if(c.getId() == id){
                    c.setName(name);
                    c.setPhone(phone);
                }
            }
        }
        contactAdapter.notifyDataSetChanged();
    }

    private void UpdateData() {
        db.addContact(new Contact(1, "Chung1", "001"));
        db.addContact(new Contact(2, "Chung2", "001"));
        db.addContact(new Contact(3, "Chung3", "001"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_menu_item1){
            Toast.makeText(this, "Sort by name", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.action_menu_item2){
            Toast.makeText(this, "Sort by phone", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.action_menu_item3){
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.context_menu_item1){
            Toast.makeText(this, "Sort by name", Toast.LENGTH_SHORT).show();

            Contact contact=listContact.get(selectedItemId);
            Intent intent = new Intent(this, SubActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", contact.getId());
            bundle.putString("name", contact.getName());
            bundle.putString("phone", contact.getPhone());
            intent.putExtras(bundle);
            startActivityForResult(intent, 110);

        }
        else if(item.getItemId()==R.id.context_menu_item2){
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            listContact.remove(selectedItemId);
            contactAdapter.notifyDataSetChanged();
        }
        else if(item.getItemId()==R.id.context_menu_item3){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +
                    listContact.get(selectedItemId).getPhone()));
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.context_menu_item4){
//            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" +
//                    listContact.get(selectedItemId).getPhone()));
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("email:" +
                    listContact.get(selectedItemId).getName()));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "Body");
            intent.setData(Uri.parse("mailto:"));
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    private void Init() {
        listView = findViewById(R.id.listView);
        fab = findViewById(R.id.fab);
        db = new MyDB(this, "ContactDB", null, 1);
        listContact = db.getAllContact();
        contactAdapter = new ContactAdapter(listContact, this);
        contactAdapter.notifyDataSetChanged();
        listView.setAdapter(contactAdapter);
        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemId=position;
                return false;
            }
        });
    }
}