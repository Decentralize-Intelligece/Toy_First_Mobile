package com.example.toyfirstmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        //create SQLllte database
        super(context, "ToyDatabase.db", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

            Log.d("adminn", "running try");
            //create admin table primary key is adminID, with columns adminID, adminName, adminPassword, account type (admin or user)
//            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS Admin(adminID INTEGER PRIMARY KEY AUTOINCREMENT, adminName TEXT(20) NOT NULL, adminPassword TEXT(10) NOT NULL, isAdmin INTEGER DEFAULT 1)");

            //create user table primary key is userID, with columns userID, userName, userPassword, account type (admin or user), Name, Address and Phone Number
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS User(userID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT(40) UNIQUE, userName TEXT(20) NOT NULL UNIQUE, userPassword TEXT(10) NOT NULL, isAdmin INTEGER DEFAULT 0, fName TEXT(20) NOT NULL, lName TEXT(20) ,address1 TEXT(20) NOT NULL, address2 TEXT(20) ,address3 TEXT(20) ,phone TEXT(15) NOT NULL)");

            //create toy category table primary key is categoryID, with columns categoryID, categoryName
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS ToyCategory(categoryID INTEGER PRIMARY KEY AUTOINCREMENT, categoryName TEXT(20) NOT NULL UNIQUE)");

            //create toy table primary key is toyID, with columns toyID, toyName, toyPrice, toyQuantity, toyCategory as foreign key from category table, toyImage
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS Toy(toyID INTEGER PRIMARY KEY AUTOINCREMENT, toyName TEXT(20) NOT NULL, toyPrice REAL(6) NOT NULL, toyQuantity INTEGER(4) NOT NULL, toyCategory INTEGER(4) NOT NULL, toyImage BLOB, FOREIGN KEY (toyCategory) REFERENCES ToyCategory(categoryID) ON DELETE CASCADE ON UPDATE CASCADE ) ");

            //create order table  with columns orderID auto increment for different username, username, orderStatus, orderDate
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS Orders(orderID INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT(20) NOT NULL, orderStatus TEXT(20) NOT NULL, orderDate TEXT(20) NOT NULL) ");

            //create OrderItems table with columns orderID, toyID, quantity orderId as foreign key from order table, toyID as foreign key from toy table
            sqLiteDatabase.execSQL("Create Table IF NOT EXISTS OrderItems(orderID INTEGER(4) NOT NULL, toyID INTEGER(4) NOT NULL, quantity INTEGER(4) NOT NULL, FOREIGN KEY (orderID) REFERENCES Orders(orderID) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY (toyID) REFERENCES Toy(toyID)) ");


            sqLiteDatabase.execSQL("INSERT INTO User('email','userName','userPassword','isAdmin','fName','lName','address1','address2','address3','phone') VALUES('admin@email.com','Admin','Admin','1','admin','toyapp','aaa','bbb','ccc','0717489293')");
            //boolean result = insertUserData("admin@email.com","Admin","Admin",true,"admin","toyapp","aaa","bbb","ccc","0777284512");

            Log.d("adminn", "running try");
           // Log.d("adminn", String.valueOf(result));


        }

        catch(Exception e){
            e.printStackTrace();
            Log.d("adminn", "running catch");
        }
        }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //insert data into user table
    public boolean insertUserData(String email, String userName, String userPassword, boolean isAdmin, String fName,String lName, String Address1, String Address2, String Address3, String Phone){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int isAdm = (isAdmin==true)?1:0;
        contentValues.put("email",email);
        contentValues.put("userName",userName);
        contentValues.put("userPassword",userPassword);
        contentValues.put("isAdmin",isAdm);
        contentValues.put("fName",fName);
        contentValues.put("lName",lName);
        contentValues.put("address1",Address1);
        contentValues.put("address2",Address2);
        contentValues.put("address3",Address3);
        contentValues.put("phone",Phone);
        long result =DB.    insert("User",null,contentValues);
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
    public boolean insertToyData( String toyName, float toyPrice, int toyQuantity, int toyCategory, byte[] toyImage){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("toyName",toyName);
        contentValues.put("toyPrice",toyPrice);
        contentValues.put("toyQuantity",toyQuantity);
        contentValues.put("toyCategory",toyCategory);
        contentValues.put("toyImage",toyImage);
        long result =DB.insert("Toy",null,contentValues);
        return result != 1;
    }

    //update category table
    public boolean updateToyCategoryData(int categoryID, String categoryName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        contentValues.put("categoryID", categoryID);
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
            if (result==1)
                return true;
            else
                return false;
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

    public Cursor getUserData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User",null);
        return cursor;
    }

    //return category name when input category id
    public String categoryName(int id){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ToyCategory where categoryID = ?",new String[]{String.valueOf(id)});
        if (cursor.moveToFirst())
            return cursor.getString(1);
        else
            return null;
    }
    //return category id when input category name
    public int categoryId(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ToyCategory where categoryName = ?",new String[]{name});
        if (cursor.moveToFirst())
            return cursor.getInt(0);
        else
            return -1;
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

    //retrieve user data by email
    public Cursor getUserDataByEmail(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User where email = ?", new String[]{email});
        return cursor;
    }

    public Cursor getToyDataOnCategory(int category){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor;
        if(category == -1){
            cursor = DB.rawQuery("Select * from Toy",null);
        }else {
            cursor = DB.rawQuery("Select * from Toy where toyCategory = ?",new String[]{String.valueOf(category)});
        }

        return cursor;
    }

    //add order
    public boolean addOrder(int orderId,String userName, String orderStatus, String orderDate) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues addOrder = new ContentValues();
        ContentValues addOrderDetail = new ContentValues();
        addOrder.put("orderID", orderId);
        addOrder.put("userName", userName);
        addOrder.put("orderStatus", orderStatus);
        addOrder.put("orderDate", orderDate);
        try {
            long result = DB.insert("Orders", null, addOrder);
            return result != -1;
        }catch (Exception e){
            return false;
        }
    }



    //retrieve last order id
    public int getLastOrderID(){
        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Orders",null);
        Cursor cursor = DB.rawQuery("SELECT * FROM Orders ORDER BY orderID DESC LIMIT 1",null);
        if (cursor.moveToFirst())
            return cursor.getInt(0);
        else
            return 0;
    }

    public boolean addOderItems(int orderId, int toyID, int quantity) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues addOrderDetail = new ContentValues();
        addOrderDetail.put("orderID", orderId);
        addOrderDetail.put("toyID", toyID);
        addOrderDetail.put("quantity", quantity);
        try {
            long result = DB.insert("OrderItems", null, addOrderDetail);
            return result != -1;
        }catch (Exception e){
            return false;
        }
    }

    //return user details by user name
    public Cursor getUserDataByUserName(String userName){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User where userName = ?", new String[]{userName});
        return cursor;
    }

    public boolean updateUser(String userName, String firstName, String lastName, String address1, String address2, String address3, String mobile) {
        boolean result=false;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fName", firstName);
        contentValues.put("lName", lastName);
        contentValues.put("address1", address1);
        contentValues.put("address2", address2);
        contentValues.put("address3", address3);
        contentValues.put("phone", mobile);
        try {
            int value=DB.update("User", contentValues, "userName = ?", new String[]{userName});
            if(value>0){
                result=true;
            }
//            result = true;
        }catch (Exception e){
            Log.d("updateUser", e.getMessage());
        }
        finally {
            DB.close();
            return result;
        }
    }

    //return order details  and order items by order id
    public Cursor getOrderDetails(int orderID){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select o.orderId,o.userName,o.orderStatus,o.orderDate,i.toyId,i.quantity from Orders as o  INNER JOIN " +
                "OrderItems as i ON o.orderId=i.orderId where o.orderID = ?", new String[]{String.valueOf(orderID)});
//        Cursor cursor = DB.rawQuery("Select * from Orders  where orderID = ?", new String[]{String.valueOf(orderID)});
        return cursor;
    }

    public Cursor getOrderDetails(String userName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select o.orderId,o.userName,o.orderStatus,o.orderDate,i.toyId,i.quantity from Orders as o  INNER JOIN " +
                "OrderItems as i ON o.orderId=i.orderId where o.userName = ?", new String[]{userName});
        return cursor;
    }

    public Cursor getOrders(String userName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Orders where userName = ?", new String[]{userName});
        return cursor;
    }
    public Cursor getOrders() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Orders ", null);
        return cursor;
    }

    public Cursor getItems(int orderId) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select t.toyName, i.quantity,t.toyPrice from Toy AS t INNER JOIN" +
                " OrderItems AS i ON t.toyId=i.toyId where i.orderId = ?", new String[]{String.valueOf(orderId)});
        return cursor;
    }

    public boolean updateOrderStatus(int toyID) {
        boolean result=false;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("orderStatus", "Delivered");
        try {
            int value=DB.update("Orders", contentValues, "orderId = ?", new String[]{String.valueOf(toyID)});
            if(value>0){
                result=true;
            }
    }
        catch (Exception e){
            Log.d("updateOrderStatus", e.getMessage());
        }
        finally {
            DB.close();
            return result;
        }
    }

    public boolean deleteOrder(int orderID) {
        SQLiteDatabase DB = this.getWritableDatabase();
        try {
            DB.delete("Orders", "orderId = ?", new String[]{String.valueOf(orderID)});
            DB.delete("OrderItems", "orderId = ?", new String[]{String.valueOf(orderID)});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean isDelivered(int orderId) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select orderStatus from Orders where orderID = ? ", new String[]{String.valueOf(orderId)});
        if (cursor.moveToFirst())
            return cursor.getString(0).equals("Delivered");
        return false;
    }



    public boolean updateToyQuantity(int toyID, int toyQuantity) {
        boolean result=false;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("toyQuantity", toyQuantity);

        try {
            int value=DB.update("Toy", contentValues, "toyID = ?", new String[]{String.valueOf(toyID)});
            if(value>0){
                result=true;
            }
//            result = true;
        }catch (Exception e){
            Log.d("updateToyQuantity", e.getMessage());
        }
        finally {
            DB.close();
            return result;
        }
    }


    public int getToyQuantity(int toyID){
        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from Orders",null);
        Cursor cursor = DB.rawQuery("SELECT * FROM Toy WHERE toyID =?",new String[]{String.valueOf(toyID)});
        if (cursor.moveToFirst()){
            Log.d("getToyQuantity", String.valueOf(cursor.getInt(3)));
            return cursor.getInt(3);}
        else
            return 0;
    }
}






