package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.toyfirstmobile.activity.toy.AdminToyStoreActivity;
import com.example.toyfirstmobile.activity.toy.AdminUpdateToyActivity;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.db.SharedPreferenceController;
import com.example.toyfirstmobile.model.ToyData;

import java.util.List;

public class AdminToyStoreAdapter extends RecyclerView.Adapter<AdminToyStoreAdapter.ViewHolder>{
    private List<ToyData> toyList;
    private LayoutInflater mInflater;
//    View mView;
    DBHelper dbHelper;

    public AdminToyStoreAdapter(Context context, List<ToyData> toyList) {
        this.mInflater = LayoutInflater.from(context);
        this.toyList = toyList;
    }

    @NonNull
    @Override
    public AdminToyStoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.admin_toy_store_card_view, parent, false);
        return new AdminToyStoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminToyStoreAdapter.ViewHolder holder, int position) {
        ToyData toy = toyList.get(position);
        holder.setData(toy.getToyID(), toy.getName(),toy.getCategory(),toy.getToyPrice(),toy.getQuantity(),toy.getImage());
    }

    @Override
    public int getItemCount() {
        return toyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtToyName;
        private TextView txtToyCategory;
        private TextView txtToyPrice;
        private TextView txtToyQty;
        private ImageView imgToyImage;
        private Button btnEditCategory;
        private Button btnDeleteCategory;
        private int toyID;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtToyName = itemView.findViewById(R.id.txtAdminToyName);
            txtToyCategory = itemView.findViewById(R.id.txtAdminCategory);
            txtToyPrice = itemView.findViewById(R.id.txtAdminToyPrice);
            txtToyQty = itemView.findViewById(R.id.txtAdminQuantity);
            imgToyImage = itemView.findViewById(R.id.imageViewAdminToyImage);

            btnEditCategory = (Button) itemView.findViewById(R.id.btnAdminToyStoreEdit);
            btnDeleteCategory = (Button) itemView.findViewById(R.id.btnAdminToyStoreDeleteToy);

            btnEditCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Hello", "edit clicked");
                    SharedPreferenceController.setCurrentToy(v.getContext(), toyID);
                    Intent intent =  new Intent(v.getContext(), AdminUpdateToyActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

            btnDeleteCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO write method to delete toys -> input toyID to delete
                    Intent intent =  new Intent(v.getContext(), AdminToyStoreActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

            dbHelper = new DBHelper(itemView.getContext());

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

        public void setData(int id, String name, int category, float toyPrice, int toyQuantity, byte[] byteArray){
            toyID = id;
            String categoryName = dbHelper.categoryName(category);
            if(categoryName == null){ categoryName = "Unlisted"; }
            this.txtToyName.setText(name);
            this.txtToyCategory.setText(  categoryName+"");
            this.txtToyQty.setText("Quantity : "+toyQuantity + "");
            this.txtToyPrice.setText("LKR " + toyPrice + "");
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            this.imgToyImage.setImageBitmap(bitmap);
        }


    }
}
