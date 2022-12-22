package com.example.toyfirstmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        //create SQLllte database
        super(context, "ToyDatabase.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            //create admin table primary key is adminID, with columns adminID, adminName, adminPassword, account type (admin or user)
//            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS Admin(adminID INTEGER PRIMARY KEY AUTOINCREMENT, adminName TEXT(20) NOT NULL, adminPassword TEXT(10) NOT NULL, isAdmin INTEGER DEFAULT 1)");

            //create user table primary key is userID, with columns userID, userName, userPassword, account type (admin or user), Name, Address and Phone Number
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS User(userID INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT(20) NOT NULL, userPassword TEXT(10) NOT NULL, isAdmin INTEGER DEFAULT 0, name TEXT(20) NOT NULL, address TEXT(20) NOT NULL, phone TEXT(15) NOT NULL)");

            //create toy category table primary key is categoryID, with columns categoryID, categoryName
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS ToyCategory(categoryID INTEGER PRIMARY KEY AUTOINCREMENT, categoryName TEXT(20) NOT NULL)");

            //create toy table primary key is toyID, with columns toyID, toyName, toyPrice, toyQuantity, toyCategory as foreign key from category table, toyImage
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS Toy(toyID INTEGER PRIMARY KEY AUTOINCREMENT, toyName TEXT(20) NOT NULL, toyPrice REAL(6) NOT NULL, toyQuantity INTEGER(4) NOT NULL, toyCategory INTEGER(4) NOT NULL, toyImage BLOB, FOREIGN KEY (toyCategory) REFERENCES ToyCategory(categoryID) ON DELETE CASCADE ON UPDATE CASCADE ) ");

            //create order table primary key is orderID, with columns orderID, username, toyId as foreign key from toy table, orderStatus, orderQuantity, orderDate

            }

        catch(Exception e){
            e.printStackTrace();
        }
        }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop Table if exists Admin");
        sqLiteDatabase.execSQL("drop Table if exists User");
        sqLiteDatabase.execSQL("drop Table if exists ToyCategory");
        sqLiteDatabase.execSQL("drop Table if exists Toy");
        sqLiteDatabase.execSQL("drop Table if exists Orders");
    }

    //not need this method. because we don't need to insert data into admin table.
    public boolean insertAdminData(int adminID, String adminName, String adminPassword, boolean isAdmin){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("adminID",adminID);
        contentValues.put("adminName",adminName);
        contentValues.put("adminPassword",adminPassword);
        contentValues.put("isAdmin",isAdmin);
        long result =DB.insert("Admin",null,contentValues);
        return result != 1;
    }

    //insert data into user table
    public boolean insertUserData(String userName, String userPassword, boolean isAdmin, String Name, String Address, String Phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int isAdm = (isAdmin==true)?1:0;
        contentValues.put("userName",userName);
        contentValues.put("userPassword",userPassword);
        contentValues.put("isAdmin",isAdm);
        contentValues.put("Name",Name);
        contentValues.put("address",Address);
        contentValues.put("phone",Phone);
        long result =DB.insert("User",null,contentValues);
        return result != 1;
    }

    //insert data into toy category table
    public boolean insertToyCategoryData(int categoryID, String categoryName){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoryID",categoryID);
        contentValues.put("categoryName",categoryName);
        long result =DB.insert("ToyCategory",null,contentValues);
        return result != 1;
    }

    //insert data into toy category table
    public boolean insertToyCategoryData(String categoryName){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoryName",categoryName);
        long result =DB.insert("ToyCategory",null,contentValues);
        return result != 1;
    }

    //insert data into toy table
    public boolean insertToyData(int toyID, String toyName, float toyPrice, int toyQuantity, int toyCategory, byte[] toyImage){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("toyID",toyID);
        contentValues.put("toyName",toyName);
        contentValues.put("toyPrice",toyPrice);
        contentValues.put("toyQuantity",toyQuantity);
        contentValues.put("toyCategory",toyCategory);
        contentValues.put("toyImage",toyImage);
        long result =DB.insert("Toy",null,contentValues);
        return result != 1;
    }

    //insert data into order table
    public boolean insertOrderData(int orderID, String userName, int toyID, String orderStatus, int orderQuantity, String orderDate){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("orderID",orderID);
        contentValues.put("userName",userName);
        contentValues.put("toyID",toyID);
        contentValues.put("orderStatus",orderStatus);
        contentValues.put("orderQuantity",orderQuantity);
        contentValues.put("orderDate",orderDate);
        long result =DB.insert("Orders",null,contentValues);
        return result != 1;
    }

    //update category table
    public boolean updateToyCategoryData(int categoryID, String categoryName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("categoryID", categoryID);
        contentValues.put("categoryName", categoryName);
        Cursor cursor = DB.rawQuery("Select * from ToyCategory where categoryID = ?", new String[]{String.valueOf(categoryID)});
        if (cursor.getCount() > 0) {
            long result = DB.update("ToyCategory", contentValues, "categoryID = ?", new String[]{String.valueOf(categoryID)});
            return result != 1;
        } else {
            return false;
        }
    }

    //delete category
    public boolean deleteToyCategoryData(int categoryID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ToyCategory where categoryID = ?", new String[]{String.valueOf(categoryID)});
        if (cursor.getCount() > 0) {
            long result = DB.delete("ToyCategory", "categoryID = ?", new String[]{String.valueOf(categoryID)});
            return result != 1;
        } else {
            return false;
        }
    }

    //retrieve data from category table
    public Cursor getToyCategoryData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ToyCategory",null);
        return cursor;
    }

    //update toy table
    public boolean updateToyData(int toyID, String toyName, float toyPrice, int toyQuantity, int toyCategory, byte[] toyImage) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("toyID", toyID);
        contentValues.put("toyName", toyName);
        contentValues.put("toyPrice", toyPrice);
        contentValues.put("toyQuantity", toyQuantity);
        contentValues.put("toyCategory", toyCategory);
        contentValues.put("toyImage", toyImage);
        Cursor cursor = DB.rawQuery("Select * from Toy where toyID = ?", new String[]{String.valueOf(toyID)});
        if (cursor.getCount() > 0) {
            long result = DB.update("Toy", contentValues, "toyID = ?", new String[]{String.valueOf(toyID)});
            return result != 1;
        } else {
            return false;
        }
    }

    //delete toy
    public boolean deleteToyData(int toyID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Toy where toyID = ?", new String[]{String.valueOf(toyID)});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Toy", "toyID = ?", new String[]{String.valueOf(toyID)});
            return result != 1;
        } else {
            return false;
        }
    }

    //retrieve data from toy table
    public Cursor getToyData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Toy",null);
        return cursor;
    }

}

