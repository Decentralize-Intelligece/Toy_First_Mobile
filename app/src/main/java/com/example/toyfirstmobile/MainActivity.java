package com.example.toyfirstmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.toyfirstmobile.activity.dashboards.AdminDashboardActivity;
import com.example.toyfirstmobile.activity.dashboards.UserDashboardActivity;
import com.example.toyfirstmobile.activity.login.UserLoginActivity;
import com.example.toyfirstmobile.activity.register.UserRegisterActivity;
import com.example.toyfirstmobile.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.btnMainRegister);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserRegisterActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.btnMainLogin);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserLoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.btnGoToUserDashboard);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(v.getContext());
                Intent intent =  new Intent(v.getContext(), UserDashboardActivity.class);
                v.getContext().startActivity(intent);
                /**
                 * take all order detail from db base on order id
                 */
                /**
                Cursor result = dbHelper.getOrderDetails(1);
                //show the order details
                if (result != null) {
                    result.moveToFirst();
                    //size of the result
                    int size = result.getCount();
                    //print the result
                    for (int i = 0; i < size; i++) {
                        Log.d("Hello", "Order ID: " + result.getString(0));
                        Log.d("Hello", "User Name: " + result.getString(1));
                        Log.d("Hello", "Order Status: " + result.getString(2));
                        Log.d("Hello", "Order Date: " + result.getString(3));
                        Log.d("Hello", "Toy ID: " + result.getString(4));
                        Log.d("Hello", "Toy Quantity: " + result.getString(5));
                        result.moveToNext();
                    }
                }
                 */
            }
        });

        Button button4 = (Button) findViewById(R.id.btnGoToAdminDashboard);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), AdminDashboardActivity.class);
                v.getContext().startActivity(intent);
            }
        });




        //test database insert
//        Vector<ShoppingCartItem> items = new Vector<ShoppingCartItem>();
//
      //items.add(new ShoppingCartItem(3,25,68));
//        items.add(new ShoppingCartItem(3,25,68));
//


    }

}