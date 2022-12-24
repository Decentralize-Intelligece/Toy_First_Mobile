package com.example.toyfirstmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.toyfirstmobile.activity.dashboards.AdminDashboardActivity;
import com.example.toyfirstmobile.activity.dashboards.UserDashboardActivity;
import com.example.toyfirstmobile.activity.login.UserLoginActivity;
import com.example.toyfirstmobile.activity.register.UserRegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.btnMainRegister);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserRegisterActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.btnMainLogin);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserLoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.btnGoToUserDashboard);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), UserDashboardActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.btnGoToAdminDashboard);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(), AdminDashboardActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

}