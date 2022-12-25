package com.example.toyfirstmobile.activity.login;
import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.dashboards.AdminDashboardActivity;
import com.example.toyfirstmobile.activity.dashboards.UserDashboardActivity;
import com.example.toyfirstmobile.db.DBHelper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserLoginActivity extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(this);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            getSupportActionBar().hide();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_login);

            Button btnLogin = (Button) findViewById(R.id.btnLogin);
            /**take email and password from user, check if email and password are correct
             * if correct, go to main page, if not, show error message
             */
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
                    String email = txtEmail.getText().toString();
                    //compare password is correct
                    EditText txtPassword = (EditText) findViewById(R.id.editTextPassword);
                    String password = txtPassword.getText().toString();
                    Log.d("email", email);
                    Log.d("password", password);

                    Cursor res = null;
                    try {
                        res = dbHelper.getUserDataByEmail(email);
                    } catch (Exception e) {
                        Log.d("error", e.getMessage());
                    }
                    if (res.getCount() == 0) {
                        Log.d("UserLoginActivity", "No data");
                    } else {
                        if (res.moveToFirst()) {
                            if (res.getString(3).equals(password)) {
                                Log.d("UserLoginActivity", "password correct");
                                //go to the user home page
                                if (res.getInt(4) == 0) {
                                    Log.d("UserLoginActivity", "user");
                                    Intent intent = new Intent(v.getContext(), UserDashboardActivity.class);
                                    v.getContext().startActivity(intent);
                                } else {
                                    Log.d("UserLoginActivity", "admin");
                                    Intent intent = new Intent(v.getContext(), AdminDashboardActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                            }
                        } else {
                            Log.d("UserLoginActivity", "password incorrect");
                        }
                    }
                    res.close();
                    dbHelper.close();
                }
            });
        }
}

