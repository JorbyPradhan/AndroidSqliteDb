package com.example.sqliteregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sqliteregistration.Database.DatabaseHandler;
import com.example.sqliteregistration.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class Main2Activity extends AppCompatActivity {
private Button Add;
private TextView Display;
private DatabaseHandler handler;
private TextInputEditText name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Add = findViewById(R.id.btn_add);
        name = findViewById(R.id.login_name);
        password = findViewById(R.id.edt_Pass);
        handler = new DatabaseHandler(this);
        Display = findViewById(R.id.btn_view);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    name.setError("Fill name");
                    password.setError("Fill Password");
                    return;
                }
                else{
                    Cursor data = handler.getLoginData(name.getText().toString().trim(),password.getText().toString().trim());
                    if (data.moveToFirst()){
                        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
        Display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
