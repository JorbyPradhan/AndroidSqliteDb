package com.example.sqliteregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteregistration.Database.DatabaseHandler;
import com.example.sqliteregistration.model.User;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private TextView name,age,dob;
    private User user;
    private DatabaseHandler handler;
    private List<User> userList;
    private EditText edtName,edtAge;
    private Button editBtn,updateBtn,DeleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        name = findViewById(R.id.txt_name);
        age = findViewById(R.id.txt_age_view);
        dob = findViewById(R.id.txt_dob_view);
        editBtn = findViewById(R.id.btn_edt);
        edtName = findViewById(R.id.edt_delete_name);
        edtAge = findViewById(R.id.edt_delete_age);
        updateBtn = findViewById(R.id.btn_update);
        DeleteBtn =findViewById(R.id.btn_Delete);
        userList = new ArrayList<>();
        handler = new DatabaseHandler(DisplayActivity.this);
        getDataView();
        //userList 5
        for (User u : userList){
            name.append(u.getName() +", ");
            age.append(u.getAge()+ ",");
            dob.append(u.getDob()+ ", ");
        }
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setVisibility(View.VISIBLE);
                edtAge.setVisibility(View.VISIBLE);
                updateBtn.setVisibility(View.VISIBLE);
                DeleteBtn.setVisibility(View.VISIBLE);
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String uname = edtName.getText().toString().trim();
                String uage = edtAge.getText().toString().trim();
                UpdatingData(uname,uage);
            }
        });
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText(null);
                age.setText(null);
                dob.setText(null);
                String dname = edtName.getText().toString().trim();
                DeleteingData(dname);
            }
        });
    }

    private void UpdatingData(String uname,String uage) {
        boolean flag = false;
        flag= handler.updateData(uage,uname);
        if(flag){
            User user = new User();
            Toast.makeText(DisplayActivity.this,"Successful",Toast.LENGTH_SHORT).show();
            user =handler.getAllContact(uname);
            name.setText(user.getName());
            age.setText(user.getAge());
            dob.setText(user.getDob());
        }
        else{
            Toast.makeText(DisplayActivity.this,"Fail",Toast.LENGTH_SHORT).show();
        }
    }
    private void DeleteingData(String dname){
       boolean flag = false;
       flag= handler.deleteData(dname);

       if(flag == true){
           getDataView();
           for (User u : userList){
               name.append(u.getName() +", ");
               age.append(u.getAge()+ ",");
               dob.append(u.getDob()+ ", ");
           }
       }

    }

    private void getDataView() {
        userList.clear();        //name//0,age//1,dob//2
        Cursor data = handler.getData();
        while (data.moveToNext()){
            String Sname = data.getString(0);
            String Sage = String.valueOf(data.getString(1));
            String Sdob = data.getString(2);
            userList.add(new User(Sname,Sdob,Sage));//
        }
    }
}
