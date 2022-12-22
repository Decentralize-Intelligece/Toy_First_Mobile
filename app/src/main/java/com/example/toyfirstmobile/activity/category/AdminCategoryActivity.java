package com.example.toyfirstmobile.activity.category;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toyfirstmobile.R;
import com.example.toyfirstmobile.adapter.CategoryAdapter;
import com.example.toyfirstmobile.db.DBHelper;
import com.example.toyfirstmobile.model.Category;

import java.util.ArrayList;
import java.util.List;

public class AdminCategoryActivity extends AppCompatActivity {
    List<Category> categoryList;
    private String newCategoryName = "";
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_select_category);

        Button btnNewCategory = (Button) findViewById(R.id.btnNewCategory);
        btnNewCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminCategoryActivity.this);
                builder.setTitle("Add new category");

                // Set up the input
                final EditText input = new EditText(AdminCategoryActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newCategoryName = input.getText().toString();
                        dbHelper.insertToyCategoryData(newCategoryName);
                        dbHelper.insertUserData("Sudharaka","2dfSvhas",false,"Sudharaka Jayanath","dddddd","0775556667");
                        initData();
                        initRecyclerView();
//
//
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

        initData();
        initRecyclerView();

    }

    private void initData() {
        categoryList = new ArrayList<>();
        Cursor cursor = dbHelper.getToyCategoryData();
        int idId = cursor.getColumnIndex("categoryID");
        int idName = cursor.getColumnIndex("categoryName");
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            categoryList.add(new Category(cursor.getInt(idId), cursor.getString(idName)));
        }

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
