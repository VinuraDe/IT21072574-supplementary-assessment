package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSupplierActivity extends AppCompatActivity {

    EditText editText_name,editText_email, editText_address,editText_phone;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        editText_name = findViewById(R.id.edittext_name);
        editText_email = findViewById(R.id.edittext_email);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);
        editText_address = findViewById(R.id.edittext_address) ;
        editText_phone = findViewById(R.id.edittext_phone);



        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringEmail = editText_email.getText().toString();
                String stringAddress = editText_address.getText().toString();
                String stringPhone = editText_phone.getText().toString();

                if (stringName.isEmpty()){
                    editText_name.setError("Supplier Name is Required");
                    editText_name.requestFocus();
                    return;

                }
                if (!Patterns.EMAIL_ADDRESS.matcher(stringEmail).matches()){
                    editText_email.setError("Invalid Email!");
                    editText_email.requestFocus();
                    return;
                }

                if(stringPhone.length() < 9){
                    editText_phone.setError("Enter valid phone number!");
                    editText_phone.requestFocus();
                    return;
                }

                if (stringName.length() <=0 || stringEmail.length() <=0 || stringAddress.length() <=0 || stringPhone.length() <=0){
                    Toast.makeText(AddSupplierActivity.this, "Enter All Field", Toast.LENGTH_SHORT).show();
                }else {
                    DBHelper dbHelper = new DBHelper(AddSupplierActivity.this);
                    SupplierModel supplierModel = new SupplierModel(stringName,stringEmail,stringAddress,stringPhone);
                    dbHelper.addSupplier(supplierModel);
                    Toast.makeText(AddSupplierActivity.this, "New Supplier Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSupplierActivity.this,ViewSupplierActivity.class);
                startActivity(intent);
            }
        });


    }
}