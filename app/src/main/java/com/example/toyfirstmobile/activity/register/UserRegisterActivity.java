package com.example.toyfirstmobile.activity.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.validation.Validation;

public class UserRegisterActivity extends AppCompatActivity {
    DBHelper dbHelper = new DBHelper(this);

//    EditText txtFName = (EditText) findViewById(R.id.editTextFirstName);
//    EditText txtLName = (EditText) findViewById(R.id.editTextLastName);
//    EditText txtUserName = (EditText) findViewById(R.id.editTextUserName);
//    EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
//    EditText txtPassword = (EditText) findViewById(R.id.editTextPassword);
//    EditText txtAddress1 = (EditText) findViewById(R.id.editTextAddress1);
//    EditText txtAddress2 = (EditText) findViewById(R.id.editTextAddress2);
//    EditText txtAddress3 = (EditText) findViewById(R.id.editTextAddress3);
//    EditText txtMobile = (EditText) findViewById(R.id.editTextMobilePhone);
//    Button btnRegister = (Button) findViewById(R.id.btnAdminAddToy);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
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
        Button btnRegister = (Button) findViewById(R.id.btnAdminAddToy);

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

                boolean check = true;
                boolean empty = false;
                if(!Validation.isValidUserName(userName)){
                    txtUserName.requestFocus();
                    txtUserName.setError("Invalidate user name");
                    check = false;
                }
                if(!Validation.isValidEmail(email)){
                    txtEmail.requestFocus();
                    txtEmail.setError("Invalidate email");
                    check = false;

                }

                if(!Validation.isValidMobileNumber(mobile)){
                    txtMobile.requestFocus();
                    txtMobile.setError("Invalid mobile number");
                    check = false;

                }

                if(fName.length()==0 || lName.length()==0||userName.length()==0||email.length()==0||password.length()==0||address1.length()==0||address2.length()==0||mobile.length()==0){
                    empty = true;
                }


                if(!check){
                    Toast.makeText(getApplicationContext(),"One or more input fields are not correct",Toast.LENGTH_SHORT).show();
                }

                else if(empty){
                    Toast.makeText(getApplicationContext(),"One or more input fields are empty",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_SHORT).show();
                    dbHelper.insertUserData(email,userName, password,false,fName , lName,address1 ,address2 , address3, mobile);
                }




                            }
        });

    }

//    private boolean validateInfo(String userName, String email, String password) {
//

//        else return true;
//
//
//
//    }

}
