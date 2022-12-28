package com.example.toyfirstmobile.activity.cart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.UserCartAdapter;
import com.example.toyfirstmobile.model.ShoppingCart;
import com.example.toyfirstmobile.model.ShoppingCartItem;

import java.util.Arrays;
import java.util.List;

//import kotlinx.coroutines.channels.Receive;

public class UserCartActivity extends AppCompatActivity {
    List<ShoppingCartItem> cartItems;
    TextView txtShoppingCartTotal;
    Button btnCartBuy;
    Button btnCartOrderCancel;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_cart);
        cartItems = Arrays.asList(ShoppingCart.getItems());
        initRecyclerView();
        txtShoppingCartTotal = (TextView) findViewById(R.id.txtCartTotal);
        btnCartBuy = (Button) findViewById(R.id.btnViewOrderShip);
        btnCartOrderCancel = (Button) findViewById(R.id.btnViewOrderCancel);

        txtShoppingCartTotal.setText("LKR " + ShoppingCart.total);



    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUserCart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        UserCartAdapter adapter = new UserCartAdapter(this, cartItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
