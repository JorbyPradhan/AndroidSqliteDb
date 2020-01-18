package com.example.sqliteregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sqliteregistration.Database.DatabaseHandler;
import com.example.sqliteregistration.model.User;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private TextView name,age,dob;
    private User user;
    private DatabaseHandler handler;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        name = findViewById(R.id.txt_name);
        age = findViewById(R.id.txt_age_view);
        dob = findViewById(R.id.txt_dob_view);
        userList = new ArrayList<>();
        handler = new DatabaseHandler(this);
        getDataView();
        for (User u : userList){
            name.append(u.getName() +", ");
            age.append(u.getAge()+ ",");
            dob.append(u.getDob()+ ", ");
        }
    }
    private void getDataView() {
        Cursor data = handler.getData();
        while (data.moveToNext()){
            String Sname = data.getString(0);
            String Sage = data.getString(1);
            String Sdob = data.getString(2);
            userList.add(new User(Sname,Sage,Sdob));
        }
    }
}
