package com.example.toyfirstmobile.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.ViewOneOrderAdapter;
import com.example.toyfirstmobile.adapter.ViewOrderAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Order;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewOneOrderActivity extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(this);
    Cursor cursor=null;
    String userName= "";
    List<Order> itemList;
    Button btnCancelOrder;
    TextView txtOrderNo;
    TextView totalAmount;
    int orderId=0;
    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_order);

        initData();
        initRecyclerView();
        btnCancelOrder = findViewById(R.id.btnOrderCancel);
        txtOrderNo = findViewById(R.id.txtOrderOrder);
        totalAmount = findViewById(R.id.txtViewOrderTotal);

        txtOrderNo.setText("#"+String.valueOf(orderId));
        totalAmount.setText("LKR " + String.valueOf(total));

        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            boolean isDeleted = false;
            boolean isDelivered = false;
            @Override
            public void onClick(View v) {
                if(dbHelper.isDelivered(orderId)){
                    Log.d("isDelivered", "true");
                    Toast.makeText(ViewOneOrderActivity.this, "Order is already delivered", Toast.LENGTH_SHORT).show();
                }else{
                    isDeleted=dbHelper.deleteOrder(orderId);
                    Log.d("isDeleted", String.valueOf(isDeleted));
                    Toast.makeText(ViewOneOrderActivity.this, "Order is cancelled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void initData() {
        itemList = new ArrayList<Order>();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderId = Integer.parseInt(extras.getString("orderID"));
        }
        try {
            cursor=dbHelper.getItems(orderId);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Log.d("item", cursor.getString(0));
                Log.d("item", cursor.getString(1));
                Log.d("item", cursor.getString(2));
                total += cursor.getInt(2)*cursor.getInt(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            itemList.add(new Order(
                    cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            ));
        }
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewViewOrder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ViewOneOrderAdapter adapter = new ViewOneOrderAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}