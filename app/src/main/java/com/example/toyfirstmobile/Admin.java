package com.example.toyfirstmobile;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

public class Admin extends User{

    public Admin(){}

    public Admin(String userName, String password) {
        super(userName, password);
    }

    //Create toy method, combine with create toy activity in the interface
    public void createToy(int toyID, String toyName, float toyPrice, int toyQuantity, int toyCategory, byte[] toyImage){
        /*
        Toy toy = new Toy();
        toy.setToyID(toyID);
        toy.setName(toyName);
        */

        DBHelper db = new DBHelper();
        boolean res=db.insertToyData(toyID, toyName, toyPrice, toyQuantity, toyCategory, toyImage);
        if(res)
//            Toast.makeText(Admin.this,"Toy created successfully", Toast.LENGTH_SHORT).show();
            System.out.println("Toy created successfully"); //its better to make toast message
        else
//            Toast.makeText("Toy created failed", Toast.LENGTH_SHORT).show();
            System.out.println("Toy created failed");
    }

    //Update toy method, combine with update toy activity in the interface
    public void updateToy(int toyID, String toyName, float toyPrice, int toyQuantity, int toyCategory, byte[] toyImage){
        DBHelper db = new DBHelper();
        boolean res=db.updateToyData(toyID, toyName, toyPrice, toyQuantity, toyCategory, toyImage);
        if(res)
//            Toast.makeText(Admin.this,"Toy updated successfully", Toast.LENGTH_SHORT).show();
            System.out.println("Toy updated successfully");
        else
//            Toast.makeText("Toy updated failed", Toast.LENGTH_SHORT).show();
            System.out.println("Toy updated failed");

    }

    //Delete toy method, combine with delete toy activity in the interface
    public void deleteToy(int toyID) {
        DBHelper db = new DBHelper();
        boolean res = db.deleteToyData(toyID);
        if (res)
//            Toast.makeText(Admin.this,"Toy deleted successfully", Toast.LENGTH_SHORT).show();
            System.out.println("Toy deleted successfully");
        else
//            Toast.makeText("Toy deleted failed", Toast.LENGTH_SHORT).show();
            System.out.println("Toy deleted failed");
    }
        //get toy data method, combine with get toy activity in the interface
        public void getToy(int toyID){
            DBHelper db = new DBHelper();
            Cursor res=db.getToyData();
            if(res.getCount() == 0) {
                // show message
                System.out.println("Error,Nothing found"); //its better to make toast message
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Toy ID :"+ res.getString(0)+"\n");
                buffer.append("Toy Name :"+ res.getString(1)+"\n");
                buffer.append("Toy Price :"+ res.getString(2)+"\n");
                buffer.append("Toy Quantity :"+ res.getString(3)+"\n");
                buffer.append("Toy Category :"+ res.getString(4)+"\n");
                buffer.append("Toy Image :"+ res.getString(5)+"\n\n");
            }
            // Show all data
//            showMessage("Toy Data",buffer.toString());
            //put above data into a table

        }


}
