package com.example.bayan_midt1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper db;
    EditText word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button back = (Button) findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

        Button next = (Button) findViewById(R.id.next);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

        db = new DatabaseHelper(this);
        Button search = (Button)findViewById(R.id.searchbttn);
        word = (EditText)findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchWord = word.getText().toString();
                if(searchWord.isEmpty()){
                    Toast.makeText(MainActivity2.this, "Empty Search!",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Cursor cursor = db.find(searchWord);
                    if(cursor.getCount() <= 0){
                        Toast.makeText(MainActivity2.this, "Item is not exist",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        StringBuffer buffer = new StringBuffer();
                        while(cursor.moveToNext()) {
                            buffer.append("ID: " + cursor.getString(0)+ "\n");
                            buffer.append("Name: " + cursor.getString(1)+ "\n");
                            buffer.append("Email: " + cursor.getString(2)+ "\n");
                            buffer.append("Phone: " + cursor.getString(3)+ "\n\n");
                        }
                        AlertDialog.Builder builder = new
                                AlertDialog.Builder(MainActivity2.this);
                        builder.setCancelable(true); // a dialog box that can be closed
                        builder.setTitle("Matching Users");
                        builder.setMessage(buffer.toString());
                        builder.show();
                        }
                    }
                }

        });
    }
}
