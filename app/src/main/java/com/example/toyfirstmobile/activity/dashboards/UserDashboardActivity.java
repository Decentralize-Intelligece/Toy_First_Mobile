package com.example.toyfirstmobile.activity.dashboards;

import androidx.appcompat.app.AppCompatActivity;
import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.category.AdminCategoryActivity;
import com.example.toyfirstmobile.activity.category.UserCategoryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_dashboard);
        Button button4 = (Button) findViewById(R.id.btnUserToyStore);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserCategoryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}