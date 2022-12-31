package com.example.toyfirstmobile.activity.order;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.AdminViewOrdersAdapter;
import com.example.toyfirstmobile.adapter.ViewOrderAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;
import com.example.toyfirstmobile.model.Order;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdminViewOrderActivity extends AppCompatActivity {
    DBHelper dbHelper=new DBHelper(this);
    Cursor cursor=null;
    String userName= "";
    List<Order> orderList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        userName = SharedPreferenceController.getCurrentUser(this);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_orders);
        initData();
        initRecyclerView();
    }


    public void initData() {
        orderList = new ArrayList<Order>();
        try {
            cursor=dbHelper.getOrders();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            orderList.add(new Order(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }
    }

    public void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.adminOrdersRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        AdminViewOrdersAdapter adapter = new AdminViewOrdersAdapter(this, orderList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    }
