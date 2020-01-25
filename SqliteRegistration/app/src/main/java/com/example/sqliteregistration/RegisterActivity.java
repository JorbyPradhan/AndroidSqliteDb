package com.example.sqliteregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqliteregistration.Database.DatabaseHandler;
import com.example.sqliteregistration.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
private TextInputEditText name,password,confirmPassword,email,phone;
private Button Register;
private DatabaseHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setId();
        handler =new DatabaseHandler(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
    }

    private void validation() {
        if(name.getText().toString().isEmpty()){
            name.setError("Fill Name");
            return;
        }
        if(email.getText().toString().isEmpty()){
            email.setError("Fill Email");
            return;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Fill Password");
            return;
        } if(phone.getText().toString().isEmpty()){
            password.setError("Fill Password");
            return;
        }
        if(confirmPassword.getText().toString().isEmpty()){
            confirmPassword.setError("Fill ConfirmPassword");
            return;
        }
        if(password.getText().toString().trim().equals(confirmPassword.getText().toString().trim())){
            boolean flag = false;
            User user = new User(name.getText().toString(),password.getText().toString(),
                    email.getText().toString(),Integer.parseInt(phone.getText().toString().trim()));
            flag =handler.RegisterData(user);
            if(flag == true){
                Toast.makeText(this,"Account Create Successful",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,Main2Activity.class));
                finish();
            }
            else {
                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
            }

        }
        else {
            confirmPassword.setError("Password does not match");
            return;
        }
    }

    private void setId() {
        name = findViewById(R.id.reg_name);
        password = findViewById(R.id.reg_edt_Pass);
        email = findViewById(R.id.reg_edt_mail);
        confirmPassword = findViewById(R.id.confirm_Pass);
        phone = findViewById(R.id.reg_phone);
        Register = findViewById(R.id.btn_register);

    }
}
