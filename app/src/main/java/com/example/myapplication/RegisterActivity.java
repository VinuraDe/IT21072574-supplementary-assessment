package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,email, repass;
    Button reg;
    TextView sin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        email = findViewById(R.id.EmailAddress);
        reg = (Button) findViewById(R.id.regBtn);
        sin = findViewById(R.id.loginText);
        DB = new DBHelper(this);
        repass = findViewById(R.id.repassword);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                String user = username.getText().toString();
                String uemail = email.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass) || TextUtils.isEmpty(uemail))
                    Toast.makeText(RegisterActivity.this, "Cannot be Empty", Toast.LENGTH_SHORT).show();

                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkuseremail(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user,pass, uemail);
                            if(insert == true){
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(RegisterActivity.this, "User already Exists ", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(RegisterActivity.this, "Password are not matching", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });




    }
}