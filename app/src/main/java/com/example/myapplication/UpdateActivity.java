package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    EditText name,phone,note,email;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add New Client");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone_number);
        note = findViewById(R.id.note);
        email = findViewById(R.id.email);
        save = findViewById(R.id.save);
        phone.setText(getIntent().getStringExtra("number"));
        String txt_name = name.getText().toString();
        String txt_phone = phone.getText().toString();
        final String txt_email = email.getText().toString();
        String txt_note = note.getText().toString();

        final Client client = new Client();
        if(!TextUtils.isEmpty(txt_email)){
            client.setEmil(txt_email);
        }
        if(!TextUtils.isEmpty(txt_name)){
            client.setName(txt_name);
        }
        if(!TextUtils.isEmpty(txt_phone)){
            client.setPhone(txt_phone);
        }
        if(!TextUtils.isEmpty(txt_note)){
            client.setNote(txt_note);
        }

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                        intent.putExtra("data", (Parcelable) client);
                        startActivity(intent);
                    }
                }
        );
    }
}
