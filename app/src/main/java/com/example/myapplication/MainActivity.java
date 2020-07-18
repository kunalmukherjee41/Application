package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
    ArrayList<String> list;
    ListView numbers;
    FloatingActionButton add;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Clients");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numbers = findViewById(R.id.numbers);
        list = new ArrayList<>();

        add = findViewById(R.id.add);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        recyclerView = findViewById(R.id.phone_number);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        recyclerView.setLayoutManager(layoutManager);


        String[] permission = {"android.permission.READ_CONTACTS"};

        int permsRequestCode = 200;
        requestPermissions(permission, permsRequestCode);
        checkSelfPermission("android.permission.READ_CONTACTS");


        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent, 1);
                    }
                }
        );
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean locationAccepted = grantResults[0]==PackageManager.PERMISSION_GRANTED;

                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            assert data != null;
            Uri contactData = data.getData();
            Cursor c = managedQuery(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                String id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (hasPhone.equalsIgnoreCase("1")) {
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);

                    phones.moveToFirst();
                    String cNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                    Intent intent = new Intent(this, UpdateActivity.class);
//                    intent.putExtra("number", cNumber);
//                    startActivity(intent);

//                    Intent intent1 = getIntent();
//                    Client client = intent1.getParcelableExtra("data");
//                    assert client != null;
////                    Toast.makeText(getApplicationContext(), cNumber + "\t" + client.getPhone(), Toast.LENGTH_SHORT).show();
//                    CustomAdapter adapter = new CustomAdapter(this, (List<Client>) client);
//                    recyclerView.setAdapter(adapter);

                    list.add(cNumber);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,list);
                    numbers.setAdapter(adapter);

                    phones.close();
                }
                c.close();
            }
        }
    }
}