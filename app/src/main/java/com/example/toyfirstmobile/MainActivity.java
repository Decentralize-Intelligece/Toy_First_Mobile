package com.example.toyfirstmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.toyfirstmobile.activity.category.AdminCategoryActivity;
import com.example.toyfirstmobile.adapter.CategoryAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Admin;
import com.example.toyfirstmobile.model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.mainactivitybtn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AdminCategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

}