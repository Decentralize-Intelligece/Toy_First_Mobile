package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.order.ViewOneOrderActivity;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Order;

import java.util.List;

public class ViewOrderAdapter extends RecyclerView.Adapter<ViewOrderAdapter.ViewHolder> {
    private DBHelper dbHelper;
    private LayoutInflater mInflater;
    private List<Order> orderList;
    private Cursor cursor = null;

    public ViewOrderAdapter(Context context, List<Order> orderList) {
        this.mInflater = LayoutInflater.from(context);
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_order_card_view, parent, false);
        return new ViewOrderAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewOrderAdapter.ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.setData(
                order.getOrderID(),
                order.getUserName(),
                order.getStatus(),
                order.getOrderDate()
        );
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtOrderID;
        private TextView txtOrderDate;
        private TextView txtOrderStatus;
        private Button btnViewOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderID = itemView.findViewById(R.id.txtUserOrdersOrderID);
            txtOrderDate = itemView.findViewById(R.id.txtUserOrderDate);
            txtOrderStatus = itemView.findViewById(R.id.txtUserOrderStatus);
            btnViewOrder = itemView.findViewById(R.id.btnUserOrderCardView);

            dbHelper = new DBHelper(itemView.getContext());

            btnViewOrder.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(v.getContext(), ViewOneOrderActivity.class);
                    intent.putExtra("orderID", txtOrderID.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void setData(int orderID, String userName, String status, String orderDate) {
            txtOrderID.setText(String.valueOf(orderID));
            txtOrderDate.setText(orderDate);
            txtOrderStatus.setText(status);
        }
    }

}
