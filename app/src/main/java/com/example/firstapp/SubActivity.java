package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SubActivity extends AppCompatActivity {
    private EditText edtId, edtName, edtPhone;
    private ImageView imgView;
    private Button btnOk, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Init();
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
    }

    private void Init() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
    }
}