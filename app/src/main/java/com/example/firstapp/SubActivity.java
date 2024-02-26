package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {
    private EditText edtId, edtName, edtPhone;
    private ImageView imgView;
    private Button btnOk, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Init();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnOk.setOnClickListener(v->{
            int id = Integer.parseInt(edtId.getText().toString().trim());
            String name = edtName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();

            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putInt("id", id);
            b.putString("name", name);
            b.putString("phone", phone);

            intent.putExtras(b);
            setResult(150, intent);
            finish();
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 101);
            }
        });

        Bundle b = getIntent().getExtras();
        if (b != null) {
            edtId.setText(b.getInt("id") + "");
            edtName.setText(b.getString("name"));
            edtPhone.setText(b.getString("phone"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            // Nhận URI của hình ảnh được chọn
            Uri selectedImageUri = data.getData();
            imgView.setImageURI(selectedImageUri);
        }
    }

    private void Init() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        imgView = findViewById(R.id.imgView);
    }
}