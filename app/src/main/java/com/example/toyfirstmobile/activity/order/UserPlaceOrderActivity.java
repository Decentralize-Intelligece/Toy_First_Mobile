package com.example.toyfirstmobile.activity.order;
import com.example.toyfirstmobile.R;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class UserPlaceOrderActivity extends AppCompatActivity {
    //load the order page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order);
    }
}
