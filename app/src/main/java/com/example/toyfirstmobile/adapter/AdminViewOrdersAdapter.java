package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.order.ViewOneOrderActivity;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Order;

import java.util.List;

public class AdminViewOrdersAdapter extends RecyclerView.Adapter<AdminViewOrdersAdapter.ViewHolder> {
    private DBHelper dbHelper;
    private LayoutInflater mInflater;
    private List<Order> orderList;
    private Cursor cursor = null;

    public AdminViewOrdersAdapter(Context context, List<Order> orderList) {
        this.mInflater = LayoutInflater.from(context);
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public AdminViewOrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.admin_order_card_view, parent, false);
        return new AdminViewOrdersAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdminViewOrdersAdapter.ViewHolder holder, int position) {
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
        private Button btnProcessOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderID = itemView.findViewById(R.id.txtAdminOrdersOrderID);
            txtOrderDate = itemView.findViewById(R.id.txtAdminOrderDate);
            txtOrderStatus = itemView.findViewById(R.id.txtAdminOrderStatus);
            btnProcessOrder = itemView.findViewById(R.id.btnAdminOrderProcess);

            dbHelper = new DBHelper(itemView.getContext());

            btnProcessOrder.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //toast message to show that the order has been processed
//                    Toast.makeText(v.getContext(), "Order has been processed", Toast.LENGTH_SHORT).show();
                    int toyID = Integer.parseInt(txtOrderID.getText().toString());
                    boolean res=dbHelper.updateOrderStatus(toyID);
                    if(res){
                        Toast.makeText(v.getContext(), "Order has been processed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(v.getContext(), "Order has not been processed", Toast.LENGTH_SHORT).show();
                    }
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
