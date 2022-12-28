package com.example.toyfirstmobile.activity.toy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.cart.UserCartActivity;
import com.example.toyfirstmobile.adapter.UserToyStoreAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;
import com.example.toyfirstmobile.activity.cart.model.ToyData;

import java.util.ArrayList;
import java.util.List;

public class UserToyStoreActivity extends AppCompatActivity {
    Button btnViewCart;
    List<ToyData> toyDataList;
    DBHelper dbHelper = new DBHelper(this);
    TextView txtCategory;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_toy_store);
        initData();
        initRecyclerView();
        txtCategory = (TextView) findViewById(R.id.txtUserCurrentCategory);
        txtCategory.setText(SharedPreferenceController.getCurrentCategory(this));
        btnViewCart = (Button) findViewById(R.id.btnUserToyStoreViewCart);


        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserCartActivity.class);
                v.getContext().startActivity(intent);
            }
        });



    }

    private void initData() {

        String currentCat = SharedPreferenceController.getCurrentCategory(UserToyStoreActivity.this);
        int currentCatID = dbHelper.categoryId(currentCat);

        toyDataList = new ArrayList<>();
        Cursor cursor = dbHelper.getToyDataOnCategory(currentCatID);
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

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUserToyStore);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        UserToyStoreAdapter adapter = new UserToyStoreAdapter(this, toyDataList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
