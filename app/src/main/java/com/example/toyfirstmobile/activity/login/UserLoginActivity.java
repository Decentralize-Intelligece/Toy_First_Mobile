package com.example.toyfirstmobile.activity.login;
import com.example.toyfirstmobile.MainActivity;
import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.category.AdminCategoryActivity;
import com.example.toyfirstmobile.activity.dashboards.AdminDashboardActivity;
import com.example.toyfirstmobile.activity.dashboards.UserDashboardActivity;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    EditText txtUserName = (EditText) findViewById(R.id.editTextUserName);
                    String userName = txtUserName.getText().toString();
                    //compare password is correct
                    EditText txtPassword = (EditText) findViewById(R.id.editTextPassword);
                    String password = txtPassword.getText().toString();
                    Log.d("username", userName);
                    Log.d("password", password);

                    Cursor res = null;
                    try {
//                        res = dbHelper.getUserDataByEmail(userName);
                        res = dbHelper.getUserDataByUserName(userName);
                    } catch (Exception e) {
                        Log.d("error", e.getMessage());
                    }
                    if (res.getCount() == 0) {
                        Log.d("UserLoginActivity", "No data");
                        Toast.makeText(getApplicationContext(),"Invalid credentials",Toast.LENGTH_SHORT).show();
                        Log.d("UserLoginActivity", "password incorrect");
                        txtUserName.setText("");
                        txtPassword.setText("");
                    } else {
                        if (res.moveToFirst()) {
                            if (res.getString(3).equals(password)) {
                                Log.d("UserLoginActivity", "password correct");
                                Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();
                                //go to the user home page
                                if (res.getInt(4) == 0) {
                                    Intent intent = new Intent(v.getContext(), UserDashboardActivity.class);
                                    //save username using shared preferences
                                    SharedPreferenceController.setCurrentUser(v.getContext(), res.getString(2));
                                    v.getContext().startActivity(intent);
                                } else {
                                    Log.d("UserLoginActivity", "admin");
                                    Intent intent = new Intent(v.getContext(), AdminDashboardActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Credentials do not match",Toast.LENGTH_SHORT).show();
                            Log.d("UserLoginActivity", "password incorrect");
                            txtUserName.setText("");
                            txtPassword.setText("");
//                            ((UserLoginActivity)v.getContext()).finish();
//                            Intent intent = new Intent(v.getContext(), MainActivity.class);
//                            v.getContext().startActivity(intent);
                        }
                    }

                    res.close();
                    dbHelper.close();
                }

            });
        }
}

