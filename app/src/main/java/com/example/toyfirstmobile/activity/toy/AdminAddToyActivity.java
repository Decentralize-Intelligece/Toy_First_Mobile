package com.example.toyfirstmobile.activity.toy;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;

import java.io.File;
import java.util.ArrayList;

public class AdminAddToyActivity extends AppCompatActivity {

    private ArrayList<String> categoryList = new ArrayList<>();
    DBHelper dbHelper = new DBHelper(this);
    Uri outPutFileUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_add_toy);
        Button btnAddImage = (Button) findViewById(R.id.btnAddToyImage);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategories);
        spinner.setAdapter(adapter);





    }



    private void initData() {
        categoryList = new ArrayList<>();
        Cursor cursor = dbHelper.getToyCategoryData();
        int idId = cursor.getColumnIndex("categoryID");
        int idName = cursor.getColumnIndex("categoryName");
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            categoryList.add(cursor.getString(idName));
        }

    }



}
