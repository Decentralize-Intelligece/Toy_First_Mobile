package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.model.Order;

import java.util.List;

public class ViewOneOrderAdapter extends RecyclerView.Adapter<ViewOneOrderAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Order> orderList;
    private Cursor cursor = null;

    public ViewOneOrderAdapter(Context context, List<Order> itemList) {
        this.mInflater = LayoutInflater.from(context);
        this.orderList = itemList;
    }

    @NonNull
    @Override
    public ViewOneOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_order_card_view, parent, false);
        return new ViewOneOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewOneOrderAdapter.ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.setData(
                order.getToyName(),
                order.getToyPrice(),
                order.getQuantity()
        );
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtToyName;
        private TextView txtToyPrice;
        private TextView txtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtToyName = itemView.findViewById(R.id.txtViewOrderToyName);
            txtToyPrice = itemView.findViewById(R.id.txtViewOrderToyPrice);
            txtQuantity = itemView.findViewById(R.id.txtViewOrderToyQty);
        }

        public void setData(String toyName, int quantity, int toyPrice) {
            txtToyName.setText(toyName);
            txtQuantity.setText(String.valueOf(quantity));
            txtToyPrice.setText(String.valueOf(toyPrice));
        }
    }
}
