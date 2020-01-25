package com.example.sqliteregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteregistration.Database.DatabaseHandler;
import com.example.sqliteregistration.model.User;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
private EditText name;
private TextView dob,age;
private Button Save,Reset;
private DatabaseHandler dbhandler;
User user;
int seY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setId();
        dbhandler = new DatabaseHandler(this);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((name.getText().toString().isEmpty() || dob.getText().toString().isEmpty())) {
                  Toast.makeText(getApplicationContext(),"Fill Data First",Toast.LENGTH_LONG).show();
                  return;
                }
                else {
                    user = new User(name.getText().toString(), dob.getText().toString(), age.getText().toString());
                    //Mg Mg // 2019 //1
                    //user = new User(newEntryImg);
                    dbhandler.addData(user);
                    startActivity(new Intent(MainActivity.this,DisplayActivity.class));
                    Toast.makeText(getApplicationContext(), "File database created!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(null);
                dob.setText(null);
                age.setText(null);
            }
        });
    }
    public void OnDateClicked(View view){
        Calendar c = Calendar.getInstance();
        final int mY=c.get(Calendar.YEAR);
        int mM=c.get(Calendar.MONTH);
        int mD=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd=new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dob.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                seY = mY-year;
                age.setText(String.valueOf(seY));


            }
        },mY,mM,mD);
        dpd.show();
    }
    private void setId() {
        name = findViewById(R.id.edt_name);
        dob = findViewById(R.id.txt_dob);
        age = findViewById(R.id.txt_age);
        Save =findViewById(R.id.btn_save);
        Reset = findViewById(R.id.btn_reset);
    }
}
