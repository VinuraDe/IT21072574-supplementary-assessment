package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSupplier extends AppCompatActivity {

    EditText editName, editEmail, editPhone, editAddress;
    Button AddBtn , ViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        editName = findViewById(R.id.PersonName);
        editEmail = findViewById(R.id.EmailAddress);
        editAddress = findViewById(R.id.Address);
        editPhone = findViewById(R.id.Phone);

        AddBtn = findViewById(R.id.AddBtn);
        ViewBtn = findViewById(R.id.button2);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  Cname = editName.getText().toString();
                String Cemail = editEmail.getText().toString();
                String Cphone = editPhone.getText().toString();
                String Caddress = editAddress.getText().toString();

                if(Cname.length() <= 0 || Cemail.length() <=0 || Cphone.length() <=0 || Caddress.length() <=0){
                    Toast.makeText(AddSupplier.this,"Feilds cannot be empty", Toast.LENGTH_SHORT).show();  //HERE++++++++++++++++++
                }
                else{
                    DBHelperSup dbHelper = new DBHelperSup(AddSupplier.this);    //HERE++++++++++++++++++++++++++++++++++++++++++++++
                    SupplierModel supplierModel = new SupplierModel(Cname,Cemail,Cphone,Caddress);
                    dbHelper.addSupplier(supplierModel);
                    Toast.makeText(AddSupplier.this, "Add Supplier Successfully", Toast.LENGTH_SHORT).show(); //HERE++++++++++++++++++++
                    finish();
                    //startActivity(getIntent());

                }

            }
        });
    }


}