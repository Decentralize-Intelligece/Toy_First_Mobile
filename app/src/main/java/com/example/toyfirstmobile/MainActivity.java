package com.example.toyfirstmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Admin admin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        admin = new Admin();
        db = new DBHelper(this);
        db.insertToyCategoryData(1,"Handmade");
        setContentView(R.layout.admin_dashboard);

    }
}