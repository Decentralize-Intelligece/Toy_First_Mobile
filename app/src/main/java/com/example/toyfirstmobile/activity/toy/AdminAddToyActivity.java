package com.example.toyfirstmobile.activity.toy;

import android.Manifest;
import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.util.ArrayList;


public class AdminAddToyActivity extends AppCompatActivity {

    private ArrayList<String> categoryList = new ArrayList<>();
    DBHelper dbHelper = new DBHelper(this);
    Uri imageUri;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_add_toy);
        Button btnAddImage = (Button) findViewById(R.id.btnAddToyImage);
        imageView = (ImageView) findViewById(R.id.imgAddedImage);

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AdminAddToyActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });


        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategories);
        spinner.setAdapter(adapter);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
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
