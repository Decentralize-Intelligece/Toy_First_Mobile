package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.activity.category.UserCategoryActivity;
import com.example.toyfirstmobile.activity.toy.UserToyStoreActivity;
import com.example.toyfirstmobile.db.SharedPreferenceController;
import com.example.toyfirstmobile.model.Category;

import java.util.List;

public class UserCategoryAdapter extends RecyclerView.Adapter<UserCategoryAdapter.ViewHolder>{

    private List<Category> categoryList;
    private LayoutInflater mInflater;

    public UserCategoryAdapter(Context context, List<Category> categoryList) {
        this.mInflater = LayoutInflater.from(context);
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public UserCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_category_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCategoryAdapter.ViewHolder holder, int position) {
        String name = categoryList.get(position).getName();
        holder.setData(name);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.btnGoToCategory);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Hello", "onclick : position : " + name.getText().toString());
                    SharedPreferenceController.setCurrentCategory(v.getContext(),name.getText().toString());
                    String currentCat = SharedPreferenceController.getCurrentCategory(v.getContext());
                    Log.d("Hello", "Current cat : " + currentCat);
                    Intent intent =  new Intent(v.getContext(), UserToyStoreActivity.class);
                    v.getContext().startActivity(intent);

                }
            });

        }

        public void setData(String name){
            this.name.setText(name);
        }


    }
}
