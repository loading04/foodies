package com.example.foodies;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies.DataModel.AppDataBase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class BagActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView ;
    RecyclerView recyclerView;
    private AppDataBase dataBase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybag);

        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recylebag);
        //set selected item
        bottomNavigationView.setSelectedItemId(R.id.bag);
        //database
        dataBase = AppDataBase.getInstance(this);










        //Bottom Navigation View behaviour
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.bag:
                        return true;

                    case R.id.discover:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.recipes:
                        startActivity(new Intent(getApplicationContext(),RecepiesActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }


}
