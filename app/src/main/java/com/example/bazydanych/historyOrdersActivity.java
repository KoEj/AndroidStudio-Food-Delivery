package com.example.bazydanych;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;

import java.util.Arrays;

public class historyOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_orders);
        recyclerView = findViewById(R.id.recycle);


        String[] s1 = getIntent().getStringArrayExtra("order");
        String[] s2 = getIntent().getStringArrayExtra("description");

//        alertDialog = new AlertDialog.Builder(this).create();
//        alertDialog.setTitle("Test1");
//
//        alertDialog.setMessage(Arrays.toString(s1));
//        alertDialog.show();;


        recyclerAdapter adapter = new recyclerAdapter(this,s1,s2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}