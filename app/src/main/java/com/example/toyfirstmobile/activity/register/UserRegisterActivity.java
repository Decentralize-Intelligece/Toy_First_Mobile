package com.example.toyfirstmobile.activity.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;

public class UserRegisterActivity extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        EditText txtFName = (EditText) findViewById(R.id.editTextFirstName);
        EditText txtLName = (EditText) findViewById(R.id.editTextLastName);
        EditText txtUserName = (EditText) findViewById(R.id.editTextUserName);
        EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText txtPassword = (EditText) findViewById(R.id.editTextPassword);
        EditText txtAddress1 = (EditText) findViewById(R.id.editTextAddress1);
        EditText txtAddress2 = (EditText) findViewById(R.id.editTextAddress2);
        EditText txtAddress3 = (EditText) findViewById(R.id.editTextAddress3);
        EditText txtMobile = (EditText) findViewById(R.id.editTextMobilePhone);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fName = txtFName.getText().toString();
                String lName = txtLName.getText().toString();
                String userName = txtUserName.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String address1 = txtAddress1.getText().toString();
                String address2 = txtAddress2.getText().toString();
                String address3 = txtAddress3.getText().toString();
                String mobile = txtMobile.getText().toString();

                dbHelper.insertUserData(email,userName, password,false,fName + " " + lName,address1 + ", " + address2 + ", " + address3, mobile);
                            }
        });

    }

}
