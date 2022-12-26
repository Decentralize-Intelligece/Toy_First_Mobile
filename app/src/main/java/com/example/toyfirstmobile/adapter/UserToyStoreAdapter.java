package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.model.ToyData;

import java.util.List;

public class UserToyStoreAdapter extends RecyclerView.Adapter<UserToyStoreAdapter.ViewHolder>{
    private List<ToyData> toyList;
    private LayoutInflater mInflater;

    public UserToyStoreAdapter(Context context, List<ToyData> toyList) {
        this.mInflater = LayoutInflater.from(context);
        this.toyList = toyList;
    }

    @NonNull
    @Override
    public UserToyStoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_toy_store_card_view, parent, false);
        return new UserToyStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserToyStoreAdapter.ViewHolder holder, int position) {
        ToyData toy = toyList.get(position);
        holder.setData(toy.getName(),toy.getCategory(),toy.getToyPrice(),toy.getQuantity(),toy.getImage());
    }

    @Override
    public int getItemCount() {
        return toyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtToyName;
        private TextView txtToyCategory;
        private TextView txtToyPrice;
        private ImageView imgToyImage;
        private Button btnAddToCart;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtToyName = itemView.findViewById(R.id.txtUserToyName);
            txtToyCategory = itemView.findViewById(R.id.txtUserCategory);
            txtToyPrice = itemView.findViewById(R.id.txtUserToyPrice);
            imgToyImage = itemView.findViewById(R.id.imageViewUserToyImage);
            btnAddToCart = itemView.findViewById(R.id.btnUserAddToCart);

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Hello", txtToyName.getText().toString() + " added to cart");
                }
            });

            itemView.setOnClickListener((v -> {
//                Log.d("Hello", "onclick : position : " + name);
            }));

//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("Hello", "onclick : position : " + name.getText().toString());
//                }
//            });
        }

        public void setData(String name, int category, float toyPrice, int toyQuantity, byte[] byteArray){
            this.txtToyName.setText(name);
            this.txtToyCategory.setText(category+"");
            this.txtToyPrice.setText("LKR " + toyPrice + "");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            this.imgToyImage.setImageBitmap(bitmap);
        }


    }
}
