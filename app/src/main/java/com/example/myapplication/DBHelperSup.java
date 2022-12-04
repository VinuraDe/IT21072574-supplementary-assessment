package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperSup  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABESE_NAME = "supplier_database";
    private static final String TABLE_NAME = "SUPPLIER";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";

    private SQLiteDatabase DB;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            "INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + "TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL, " +PHONE+ " TEXT NOT NULL, "+ ADDRESS +"TEXT NOT NULL );";

    public DBHelperSup (Context context){
        super(context, DATABESE_NAME, null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public void addSupplier(SupplierModel supplierModel){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperSup.NAME, supplierModel.getName());
        contentValues.put(DBHelperSup.EMAIL, supplierModel.getEmail());
        contentValues.put(DBHelperSup.PHONE, supplierModel.getPhone());
        contentValues.put(DBHelperSup.ADDRESS, supplierModel.getAddress());

        DB = this.getReadableDatabase();
        DB.insert(DBHelperSup.TABLE_NAME, null, contentValues);


    }

}

