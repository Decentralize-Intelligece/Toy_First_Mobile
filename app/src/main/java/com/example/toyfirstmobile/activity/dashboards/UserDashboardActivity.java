package com.example.toyfirstmobile.activity.dashboards;

import androidx.appcompat.app.AppCompatActivity;
import com.example.toyfirstmobile.R;
import android.os.Bundle;

public class UserDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);
    }
}