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

        DBHelper dbHelper = new DBHelper(this);
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





        //test database insert
//        Vector<ShoppingCartItem> items = new Vector<ShoppingCartItem>();
//
      //items.add(new ShoppingCartItem(3,25,68));
//        items.add(new ShoppingCartItem(3,25,68));
//


    }

}