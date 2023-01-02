package com.example.toyfirstmobile.adapter;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.cart.UserCartActivity;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.ShoppingCart;
import com.example.toyfirstmobile.model.ShoppingCartItem;

import java.util.List;

public class UserCartAdapter extends RecyclerView.Adapter<UserCartAdapter.ViewHolder>{

    private List<ShoppingCartItem> itemList;
    private LayoutInflater mInflater;
    private DBHelper dbHelper;


    public UserCartAdapter(Context context, List<ShoppingCartItem> itemList,DBHelper dbHelper) {
        this.mInflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.dbHelper = dbHelper;
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
                    //get the total items for that particular item

                    int toyQuantityStore = dbHelper.getToyQuantity(toyID);
                     ShoppingCartItem[] cartItems= ShoppingCart.getItems();

                     //get the item with the given id from the cart item list
                    int currentTotalItems = 2;
                    for(int i=0;i<cartItems.length;i++){
                        if(cartItems[i].getToyID()==toyID)currentTotalItems=cartItems[i].getQuantity();
                    }
                    Log.d("Toys", toyQuantityStore + " amount");
                    Log.d("Toys", currentTotalItems + " amount");
                    if(currentTotalItems<toyQuantityStore){
                        ShoppingCart.addItem(new ShoppingCartItem(name,toyID,1,itemCost));
                    }
                    else{
                        Toast.makeText(v.getContext(),"Stock not available",Toast.LENGTH_SHORT).show();
                    }


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
