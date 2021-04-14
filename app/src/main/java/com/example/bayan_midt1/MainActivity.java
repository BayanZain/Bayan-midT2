package com.example.bayan_midt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id, name, email, phone;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button go2 = (Button) findViewById(R.id.next);
        go2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        Button go3 = (Button) findViewById(R.id.next);


        go3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });


        Button insert = (Button)findViewById(R.id.insert);

        id = (EditText)findViewById(R.id.et_id);
        name = (EditText)findViewById(R.id.et_name);
        email = (EditText)findViewById(R.id.et_email);
        phone = (EditText)findViewById(R.id.et_phone);

        db = new DatabaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idT = id.getText().toString();
                String nameT = name.getText().toString();
                String emailT = email.getText().toString();
                String phoneT = phone.getText().toString();

                if(idT.isEmpty()||nameT.isEmpty()||emailT.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill the boxes with star sign",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    if(!db.addData(idT,nameT,emailT,phoneT)){
                        Toast.makeText(MainActivity.this, "Insertion failed",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Insertion Successful",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }


        });

    }
}