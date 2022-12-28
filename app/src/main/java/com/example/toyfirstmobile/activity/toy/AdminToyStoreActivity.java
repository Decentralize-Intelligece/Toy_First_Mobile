package com.example.toyfirstmobile.activity.toy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.AdminToyStoreAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.ToyData;

import java.util.ArrayList;
import java.util.List;

public class AdminToyStoreActivity extends AppCompatActivity {
    Button btnGotoAddToy;
    List<ToyData> toyDataList;
    DBHelper dbHelper = new DBHelper(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_toy_store);
        initData();
        initRecyclerView();


        btnGotoAddToy = (Button) findViewById(R.id.btnUserToyStoreViewCart);


        btnGotoAddToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), AdminAddToyActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }

    private void initData() {
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

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewAdminToyStore);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        AdminToyStoreAdapter adapter = new AdminToyStoreAdapter(this, toyDataList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
