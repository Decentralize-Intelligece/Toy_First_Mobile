package com.example.toyfirstmobile.adapter;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.cart.UserCartActivity;
import com.example.toyfirstmobile.model.ShoppingCart;
import com.example.toyfirstmobile.model.ShoppingCartItem;
import com.example.toyfirstmobile.model.ToyData;

import java.util.List;

public class UserCartAdapter extends RecyclerView.Adapter<UserCartAdapter.ViewHolder>{

    private List<ShoppingCartItem> itemList;
    private LayoutInflater mInflater;

    public UserCartAdapter(Context context, List<ShoppingCartItem> itemList) {
        this.mInflater = LayoutInflater.from(context);
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public UserCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_cart_card_view, parent, false);
        return new UserCartAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserCartAdapter.ViewHolder holder, int position) {
        ShoppingCartItem item = itemList.get(position);
        holder.setData(item.getToyID(),item.getName(),item.getQuantity(),item.getCost());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtCartToyQuantity;
        private TextView txtCartCardToyPrice;
        private TextView txtCartToyName;
        private Button btnCartPlus;
        private Button btnCartMinus;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCartToyQuantity = itemView.findViewById(R.id.txtUserCartToyQuantity);
            txtCartCardToyPrice = itemView.findViewById(R.id.txtUserCartCardToyPrice);
            txtCartToyName = itemView.findViewById(R.id.txtUserCartToyName);
            btnCartPlus  = itemView.findViewById(R.id.btnUserCartPlus);
            btnCartMinus  = itemView.findViewById(R.id.btnUserCartMinus);





        }

        public void setData(int toyID,String name,int toyQuantity,float itemCost){
            this.txtCartToyName.setText(name);
            this.txtCartToyQuantity.setText(""+toyQuantity);
            this.txtCartCardToyPrice.setText("LKR " + (itemCost*toyQuantity) + "");
            btnCartPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CartA", txtCartToyName.getText().toString() + " increased");
                    ShoppingCart.addItem(new ShoppingCartItem(name,toyID,1,itemCost));

                    Intent intent =  new Intent(v.getContext(), UserCartActivity.class);
                    v.getContext().startActivity(intent);


                }


            });

            btnCartMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CartA", txtCartToyName.getText().toString() + " decreased");
                    ShoppingCart.removeItem(new ShoppingCartItem(name,toyID,1,itemCost));

                    Intent intent =  new Intent(v.getContext(), UserCartActivity.class);
                    v.getContext().startActivity(intent);

                }
            });




        }
    }


}
