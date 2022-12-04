package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

class LoginActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editemail, editpassword;
    private Button login;
    DBHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editemail = findViewById(R.id.EmailAddress);
        editpassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Login Successful...", Toast.LENGTH_SHORT).show();
                String email = editemail.getText().toString();
                String pass = editpassword.getText().toString();

                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this,"Fields cannot be empty", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkpassword(email,pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this,"Login Successful...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Login failed... Try again....",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

}
