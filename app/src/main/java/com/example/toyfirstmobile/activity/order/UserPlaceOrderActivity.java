package com.example.toyfirstmobile.activity.order;
import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;
import com.example.toyfirstmobile.model.ShoppingCart;
import com.example.toyfirstmobile.model.ShoppingCartItem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class UserPlaceOrderActivity extends AppCompatActivity {
    List<ShoppingCartItem> cartItems;
    DBHelper dbHelper=new DBHelper(this);
    Button btnPlaceOrder;
    String orderDate="",userName="",orderStatus="ordered",
            firstName="",lastName="",address1="",address2="",address3="",mobile="";
    //load the order page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order);
        btnPlaceOrder = (Button) findViewById(R.id.buttonPlaceOrder);
        cartItems = Arrays.asList(ShoppingCart.getItems());

        //SharedPreferenceController.setCurrentUser(UserPlaceOrderActivity.this, "pasindum");
        userName = SharedPreferenceController.getCurrentUser(UserPlaceOrderActivity.this);

        EditText txtFName = (EditText) findViewById(R.id.editTextFirstName);
        EditText txtLName = (EditText) findViewById(R.id.editTextLastName);
        EditText txtAddress1 = (EditText) findViewById(R.id.editTextAddress1);
        EditText txtAddress2 = (EditText) findViewById(R.id.editTextAddress2);
        EditText txtAddress3 = (EditText) findViewById(R.id.editTextAddress3);
        EditText txtMobile = (EditText) findViewById(R.id.editTextMobilePhone);


        //get the user's information from the database getDataByUserName
        try {
            Cursor cursor = dbHelper.getUserDataByUserName(userName);
            if (cursor != null) {
                cursor.moveToFirst();
                firstName = cursor.getString(5);
                lastName = cursor.getString(6);
                address1 = cursor.getString(7);
                address2 = cursor.getString(8);
                address3 = cursor.getString(9);
                mobile = cursor.getString(10);
            }
            txtFName.setText(firstName);
            txtLName.setText(lastName);
            txtAddress1.setText(address1);
            txtAddress2.setText(address2);
            txtAddress3.setText(address3);
            txtMobile.setText(mobile);
        }
        catch (Exception e){
            Log.e("Error-getting-username",e.getMessage());
        }

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName1=txtFName.getText().toString();
                String lastName1=txtLName.getText().toString();
                String address11=txtAddress1.getText().toString();
                String address21=txtAddress2.getText().toString();
                String address31=txtAddress3.getText().toString();
                String mobile1=txtMobile.getText().toString();
                boolean updateUser=false;
                if (firstName.equals(firstName1) && lastName.equals(lastName1) && address1.equals(address11) &&
                        address2.equals(address21) && address3.equals(address31) && mobile.equals(mobile1)) {
                    Log.d("update-user", "User details are not changed");
                }
                else {
                    Log.d("update-user", "User details are changed");
                    //update the user's information in the database
                    try {
                        updateUser=dbHelper.updateUser(userName,firstName1,lastName1,address11,address21,address31,mobile1);
                        Log.d("update-user", String.valueOf(updateUser));
                    }
                    catch (Exception e){
                        Log.e("Error-updating-user",e.getMessage());
                    }
                }

                int orderId = dbHelper.getLastOrderID();
                orderId++;
                orderDate = java.text.DateFormat.getDateTimeInstance().format(java.util.Calendar.getInstance().getTime());
                boolean res=false;
                Log.d("pref_userName", userName);
                res = dbHelper.addOrder(orderId,userName,orderStatus,orderDate);

                if (cartItems.size() > 0 && res) {
                    for (ShoppingCartItem item : cartItems) {
                        Log.d("cart items", item.getName());
                        Log.d("cart items", String.valueOf(item.getQuantity()));
                        Log.d("cart items", String.valueOf(item.getToyID()));
                        Log.d("cart items", String.valueOf(item.getCost()));

                        res=dbHelper.addOderItems(orderId, item.getToyID(), item.getQuantity());
                        Log.d("addOrder", String.valueOf(res));
                    }


                }
                Cursor result = dbHelper.getOrderDetails(1);
                //show the order details
                if (result != null) {
                    result.moveToFirst();
                    //size of the result
                    int size = result.getCount();
                    Log.d("order details", String.valueOf(size));
                    Log.d("orderID", result.getString(0));
                    Log.d("userName", result.getString(1));
                    Log.d("orderStatus", result.getString(2));
                    Log.d("orderDate", result.getString(3));
                }

                Intent intent = new Intent(UserPlaceOrderActivity.this, UserOrderConfirmationActivity.class);
                //close the cart activity
                intent.putExtra("OrderId", orderId);
                (UserPlaceOrderActivity.this).finish();
                startActivity(intent);

            }
        });

    }
}
