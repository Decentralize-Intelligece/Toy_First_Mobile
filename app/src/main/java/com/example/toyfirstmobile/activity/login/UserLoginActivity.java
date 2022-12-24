package com.example.toyfirstmobile.activity.login;
import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.user.UserHomeActivity;
import com.example.toyfirstmobile.db.DBHelper;

import android.content.Context;
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
                    String email=txtEmail.getText().toString();
                    //compare password is correct
                    EditText txtPassword = (EditText) findViewById(R.id.editTextPassword);
                    String password=txtPassword.getText().toString();
                    Log.d("password",password);
//                    dbHelper.getUserDataByEmail(email);
                    Log.d("email",email);
                    Cursor res=null;
                    try {
                        res= dbHelper.getUserDataByEmail(email);

                    }
                    catch (Exception e){
                        Log.d("error",e.getMessage());
                    }
                    if(res.getCount()==0){
                        Log.d("UserLoginActivity","No data");
                    }
                    else{
                        while(res.moveToNext()){
                            Log.d("UserLoginActivity","email: "+res.getString(1));
                            Log.d("UserLoginActivity","username: "+res.getString(2));
                            Log.d("UserLoginActivity","password: "+res.getString(3));
                            Log.d("UserLoginActivity","isadmin: "+res.getString(4));
                            Log.d("UserLoginActivity","name: "+res.getString(5));
                            Log.d("UserLoginActivity","address: "+res.getString(6));
                            Log.d("UserLoginActivity","mobile: "+res.getString(7));
                        }

                        if(res.moveToFirst()) {
                            if (res.getString(3).equals(password)) {
                                Log.d("UserLoginActivity", "password correct");
                                //go to the user home page
                                Intent intent = new Intent(v.getContext(), UserHomeActivity.class);
                                startActivity(intent);

                            } else {
                                Log.d("UserLoginActivity", "password incorrect");
                            }
                        }
                    }
                    res.close();
                    dbHelper.close();

                }
            });
        }
}

