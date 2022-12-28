package com.example.toyfirstmobile.activity.cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.order.UserPlaceOrderActivity;
import com.example.toyfirstmobile.adapter.UserCartAdapter;

import com.example.toyfirstmobile.model.ShoppingCart;
import com.example.toyfirstmobile.model.ShoppingCartItem;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;


import java.util.Arrays;
import java.util.List;

//import kotlinx.coroutines.channels.Receive;

public class UserCartActivity extends AppCompatActivity {
    List<ShoppingCartItem> cartItems;
    TextView txtShoppingCartTotal;
    Button btnCartBuy;
    Button btnCartOrderCancel;
    DBHelper dbHelper=new DBHelper(this);

    private int itemId,toyId,quantity=0;
    private String userName="",orderDate="",orderStatus="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_cart);
        cartItems = Arrays.asList(ShoppingCart.getItems());
        initRecyclerView();
        txtShoppingCartTotal = (TextView) findViewById(R.id.txtCartTotal);

        btnCartOrderCancel = (Button) findViewById(R.id.btnCartOrderCancel);
        btnCartBuy = (Button) findViewById(R.id.btnCartOrderBuy);
        
//        btnCartBuy1 = (Button) findViewById(R.id.btnCartOrderBuy);
//        btnCartOrderCancel1 = (Button) findViewById(R.id.btnCartOrderCancel);

        txtShoppingCartTotal.setText("LKR " + ShoppingCart.total);

        btnCartBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //print cart items
                if (cartItems.size() > 0) {
                    //take current date as a string
                    orderDate = java.text.DateFormat.getDateTimeInstance().format(java.util.Calendar.getInstance().getTime());
                    SharedPreferenceController.setCurrentUser(UserCartActivity.this, "userName");
                    userName = SharedPreferenceController.getCurrentUser(v.getContext());
                    boolean res=false;
                    Log.d("pref_userName", userName);
                    for (ShoppingCartItem item : cartItems) {
                        Log.d("cartitems", item.getName());
                        Log.d("cartitems", String.valueOf(item.getQuantity()));
                        Log.d("cartitems", String.valueOf(item.getToyID()));
                        Log.d("cartitems", String.valueOf(item.getCost()));

                        res=dbHelper.addOrder(userName, item.getToyID(), "ordered", item.getQuantity(), orderDate);
                        Log.d("addOrder", String.valueOf(res));
                    }

                    Intent intent = new Intent(v.getContext(), UserPlaceOrderActivity.class);
                    v.getContext().startActivity(intent);
                }
                else {
                    Log.d("cartitems", "cart is empty");
                }
            }
        });
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
