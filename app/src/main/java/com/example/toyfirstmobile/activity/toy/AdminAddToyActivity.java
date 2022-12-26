package com.example.toyfirstmobile.activity.toy;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class AdminAddToyActivity extends AppCompatActivity {

    private ArrayList<String> categoryList = new ArrayList<>();
    private DBHelper dbHelper = new DBHelper(this);
    private Uri imageUri;
    private ImageView imageView;
    Bitmap imageToStore;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte;
    private EditText txtToyName;
    private EditText txtPrice;
    private EditText txtQuantity;
    private Spinner spinner;
    private Button btnAddToy;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_add_toy);
        Button btnAddImage = (Button) findViewById(R.id.btnAddToyImage);
        imageView = (ImageView) findViewById(R.id.imgAddedImage);
        txtToyName = (EditText) findViewById(R.id.txtToyName);
        txtPrice = (EditText) findViewById(R.id.txtToyPrice);
        txtQuantity = (EditText) findViewById(R.id.txtToyQuantity);
        btnAddToy = (Button) findViewById(R.id.btnAdminAddToy);

        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);

        spinner = (Spinner) findViewById(R.id.spinnerCategories);
        spinner.setAdapter(adapter);

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

        btnAddToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toyName = txtToyName.getText().toString();
                float toyPrice = Float.parseFloat(txtPrice.getText().toString());
                int toyQuantity = Integer.parseInt(txtQuantity.getText().toString());
                int toyCategory = Integer.parseInt(spinner.getSelectedItem().toString().split("-")[0]);

                Log.d("Hello",toyName + " " + toyPrice + " " + toyQuantity + " " + toyCategory);
                dbHelper.insertToyData(5,toyName,toyPrice,toyQuantity,toyCategory,imageInByte);
                Intent intent =  new Intent(v.getContext(), AdminToyStoreActivity.class);
                v.getContext().startActivity(intent);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                byteArrayOutputStream = new ByteArrayOutputStream();
                imageToStore.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                imageInByte = byteArrayOutputStream.toByteArray();


            } catch (IOException e) {
                e.printStackTrace();
            }
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
            categoryList.add(cursor.getString(idId) + "-" + cursor.getString(idName));
        }

    }
}
