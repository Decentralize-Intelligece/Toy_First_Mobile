package com.example.toyfirstmobile.activity.dashboards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.category.AdminCategoryActivity;
import com.example.toyfirstmobile.activity.toy.AdminAddToyActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);

        Button btnAdminCategories = (Button) findViewById(R.id.btnAdminCategories);
        btnAdminCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), AdminCategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button btnAdminAddToys = (Button) findViewById(R.id.btnAdminToyStore);
        btnAdminAddToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), AdminAddToyActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
