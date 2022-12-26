package com.example.toyfirstmobile.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.MainActivity;
import com.example.toyfirstmobile.activity.category.AdminCategoryActivity;
import com.example.toyfirstmobile.activity.dashboards.AdminDashboardActivity;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Category;
import com.example.toyfirstmobile.R;

import java.util.ArrayList;
import java.util.List;

public class AdminCategoryAdapter extends RecyclerView.Adapter<AdminCategoryAdapter.ViewHolder> {
    private List<Category> categoryList;
    private LayoutInflater mInflater;
    private DBHelper dbHelper;
    private Context adapterContext;
    private String updatedCategoryName = "";

    public AdminCategoryAdapter(Context context, List<Category> categoryList, DBHelper dbHelper) {
        this.mInflater = LayoutInflater.from(context);
        this.categoryList = categoryList;
        this.dbHelper=dbHelper;
        this.adapterContext=context;
    }
    DBHelper dbHelper1=new DBHelper(adapterContext);

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
                    Log.d("onclick del : " , name.getText().toString());
                    int catId=dbHelper.categoryId(name.getText().toString());
                    boolean res;
                    if (catId!=-1) {
                        res = dbHelper.deleteToyCategoryData(catId);
                        Log.d("onclick del ", String.valueOf(res));
                    }else{
                        Log.d("onclick del","no category");
                    }
                    Intent intent = new Intent(v.getContext(), AdminCategoryActivity.class);
                    ((AdminCategoryActivity)adapterContext).finish();
                    v.getContext().startActivity(intent);
                    dbHelper.close();
                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Update the category name");
                    // Set up the input
                    final EditText input = new EditText(v.getContext());
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            updatedCategoryName = input.getText().toString();
                            int catId=dbHelper.categoryId(name.getText().toString());
                            boolean res;
                            if (catId!=-1) {
                                res = dbHelper.updateToyCategoryData(catId, updatedCategoryName);
                                Log.d("onclick del ", String.valueOf(res));
                            }else{
                                Log.d("onclick del","no category");
                            }
                            //end the activity

                            Intent intent = new Intent(v.getContext(), AdminCategoryActivity.class);
                            ((AdminCategoryActivity)adapterContext).finish();
                            v.getContext().startActivity(intent);
                            dbHelper.close();

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            });

        }

        public void setData(String name){
            this.name.setText(name);
        }


    }

}
