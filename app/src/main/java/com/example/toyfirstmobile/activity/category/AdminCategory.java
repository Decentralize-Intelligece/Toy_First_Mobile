package com.example.toyfirstmobile.activity.category;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.CategoryAdapter;
import com.example.toyfirstmobile.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AdminCategory extends AppCompatActivity {
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_select_category);

        initData();
        initRecyclerView();

    }

    private void initData() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Gate"));
        categoryList.add(new Category(2, "Gate"));
        categoryList.add(new Category(3, "Bake"));

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
