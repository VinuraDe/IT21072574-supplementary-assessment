package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewSupplierActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supplier);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper dbHelper = new DBHelper(this);
        List<SupplierModel> supplierModels = dbHelper.getSupplierList();

        if (supplierModels.size() > 0){
            SupplierAdapter supplierAdapter = new SupplierAdapter(supplierModels,ViewSupplierActivity.this);
            recyclerView.setAdapter(supplierAdapter);
        }else {
            Toast.makeText(this, "No Suppliers in the database", Toast.LENGTH_SHORT).show();
        }




    }
}