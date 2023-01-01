package com.example.toyfirstmobile.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toyfirstmobile.R;

public class UserOrderConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confirmation);
        TextView txtOrderNumber = (TextView) findViewById(R.id.editTextOrderNumber);

        //get the order number from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int orderNumber = extras.getInt("OrderId");
            txtOrderNumber.setText(String.valueOf(orderNumber));
        }

        Button btnViewOrders = (Button) findViewById(R.id.btnAdminToyStore);
        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserViewOrderActivity.class);
                //close all activities before going to the main activity
                (UserOrderConfirmationActivity.this).finish();
                startActivity(intent);
            }
        });


    }
}
