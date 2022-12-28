package com.example.toyfirstmobile.activity.toy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;
import com.example.toyfirstmobile.model.ToyData;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AdminUpdateToyActivity extends AppCompatActivity {

    List<ToyData> toyDataList;

    ToyData currentToy;

    private ArrayList<String> categoryList = new ArrayList<>();
    private DBHelper dbHelper = new DBHelper(this);
    private Uri imageUri;
    private ImageView imageView;
    Bitmap imageToStore;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte = null;
    private EditText txtToyName;
    private EditText txtPrice;
    private EditText txtQuantity;
    private Spinner spinner;
    private Button btnUpdateToy;
    int currentToyId;

    Bitmap defualtImage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        defualtImage = ((BitmapDrawable) ResourcesCompat.getDrawable(this.getResources(), R.drawable.defaulttoyimage, null)).getBitmap();


        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.admin_update_toy);
        Button btnAddUpdateImage = (Button) findViewById(R.id.btnUpdateAddToyImage);
        imageView = (ImageView) findViewById(R.id.imgUpdatedImage);
        txtToyName = (EditText) findViewById(R.id.txtUpdatedToyName);
        txtPrice = (EditText) findViewById(R.id.txtUpdatedToyPrice);
        txtQuantity = (EditText) findViewById(R.id.txtUpdatedToyQuantity);
        btnUpdateToy = (Button) findViewById(R.id.btnAdminUpdateToy);
        imageView.setBackgroundResource(R.drawable.defaulttoyimage);


        currentToyId = SharedPreferenceController.getCurrentToy(AdminUpdateToyActivity.this);

        initData();
        initToyData();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryList);

        spinner = (Spinner) findViewById(R.id.spinnerUpdateCategories);
        spinner.setAdapter(adapter);


//        setDefaultImage();


        btnAddUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AdminUpdateToyActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        btnUpdateToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toyName = txtToyName.getText().toString();
                float toyPrice = Float.parseFloat(txtPrice.getText().toString());
                int toyQuantity = Integer.parseInt(txtQuantity.getText().toString());
                int toyCategory = Integer.parseInt(spinner.getSelectedItem().toString().split("-")[0]);

                Log.d("Hello",toyName + " " + toyPrice + " " + toyQuantity + " " + toyCategory);

                if(imageInByte == null){
                    setDefaultImage();
                }

//                dbHelper.insertToyData(toyName,toyPrice,toyQuantity,toyCategory,imageInByte);
                // TODO -> update toy function
                dbHelper.updateToyData(currentToyId,toyName,toyPrice,toyQuantity,toyCategory,imageInByte);

                //dbHelper.insertToyData(5,toyName,toyPrice,toyQuantity,toyCategory,imageInByte);
                Intent intent =  new Intent(v.getContext(), AdminToyStoreActivity.class);
                v.getContext().startActivity(intent);

            }
        });



    }

    protected void setDefaultImage(){
        imageInByte = currentToy.getImage();

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

    private void initToyData() {
        toyDataList = new ArrayList<>();
        Cursor cursor = dbHelper.getToyData();
        int idID = cursor.getColumnIndex("toyID");
        int idName = cursor.getColumnIndex("toyName");
        int idPrice = cursor.getColumnIndex("toyPrice");
        int idQty = cursor.getColumnIndex("toyQuantity");
        int idCategory = cursor.getColumnIndex("toyCategory");
        int idImage = cursor.getColumnIndex("toyImage");



        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            toyDataList.add(new ToyData(
                    cursor.getInt(idID),
                    cursor.getString(idName),
                    cursor.getFloat(idPrice),
                    cursor.getInt(idQty),
                    cursor.getInt(idCategory),
                    cursor.getBlob(idImage)
            ));
        }


        for (ToyData toy : toyDataList){
            if(toy.getToyID() == currentToyId){
                currentToy = toy;
                break;
            }
        }

        txtToyName.setText(currentToy.getName());
        txtPrice.setText(currentToy.getToyPrice()+"");
        txtQuantity.setText(currentToy.getQuantity()+"");
        imageInByte = currentToy.getImage();
        ByteArrayInputStream is = new ByteArrayInputStream(imageInByte);
        Drawable drw = Drawable.createFromStream(is, "articleImage");
        imageView.setImageDrawable(drw);

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
