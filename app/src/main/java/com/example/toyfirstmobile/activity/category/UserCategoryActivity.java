package com.example.toyfirstmobile.activity.category;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.AdminCategoryAdapter;
import com.example.toyfirstmobile.adapter.UserCategoryAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Category;

import java.util.ArrayList;
import java.util.List;

public class UserCategoryActivity extends AppCompatActivity {
    List<Category> categoryList;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select_category);
        initData();
        initRecyclerView();
//        Button btnNewCategory = (Button) findViewById(R.id.btnGetAllCategories);


        initData();
        initRecyclerView();

    }

    private void initData() {
        categoryList = new ArrayList<>();
        Cursor cursor = dbHelper.getToyCategoryData();
        int idId = cursor.getColumnIndex("categoryID");
        int idName = cursor.getColumnIndex("categoryName");
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            categoryList.add(new Category(cursor.getInt(idId), cursor.getString(idName)));
        }

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.userCategoryRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        UserCategoryAdapter adapter = new UserCategoryAdapter(this, categoryList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}

