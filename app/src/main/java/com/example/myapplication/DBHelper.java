package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.SupplierModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "supplier_database";
    private static final String TABLE_NAME = "SUPPLIER";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String PHONE = "phone";
    private SQLiteDatabase sqLiteDatabase;



    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL, "+ADDRESS+" TEXT NOT NULL,"+PHONE+" TEXT NOT NULL);";

    public DBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addSupplier(SupplierModel supplierModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, supplierModel.getName());
        contentValues.put(DBHelper.EMAIL, supplierModel.getEmail());
        contentValues.put(DBHelper.ADDRESS,supplierModel.getAddress());
        contentValues.put(DBHelper.PHONE, supplierModel.getPhone());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null,contentValues);
    }

    public List<SupplierModel> getSupplierList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<SupplierModel> storeSupplier = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String address = cursor.getString(3);
                String phone = cursor.getString(4);
                storeSupplier.add(new SupplierModel(id,name,email,address,phone));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeSupplier;
    }

    public void updateSupplier(SupplierModel supplierModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME,supplierModel.getName());
        contentValues.put(DBHelper.EMAIL,supplierModel.getEmail());
        contentValues.put(DBHelper.ADDRESS,supplierModel.getAddress());
        contentValues.put(DBHelper.PHONE,supplierModel.getPhone());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(supplierModel.getId())});
    }

    public void deleteSupplier(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}