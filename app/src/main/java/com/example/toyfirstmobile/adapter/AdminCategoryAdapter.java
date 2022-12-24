package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.model.Category;
import com.example.toyfirstmobile.R;

import java.util.List;

public class AdminCategoryAdapter extends RecyclerView.Adapter<AdminCategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private LayoutInflater mInflater;

    public AdminCategoryAdapter(Context context, List<Category> categoryList) {
        this.mInflater = LayoutInflater.from(context);
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public AdminCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.admin_category_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCategoryAdapter.ViewHolder holder, int position) {
        String name = categoryList.get(position).getName();
        holder.setData(name);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private Button name;
        private Button delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.btnEditCategory);
            delete = itemView.findViewById(R.id.btnDeleteCategory);
            itemView.setOnClickListener((v -> {
                Log.d("Hello", "onclick : position : " + name);
            }));

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Hello", "onclick : position : " + name.getText().toString());
                }
            });
        }

        public void setData(String name){
            this.name.setText(name);
        }


    }
}
