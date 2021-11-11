package com.example.foodies;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies.DataModel.AppDataBase;
import com.example.foodies.DataModel.Food;
import com.example.foodies.DataModel.FoodListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class RecepiesActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView ;
    RecyclerView recyclerView;
    Button addRecepies;
    private AppDataBase dataBase;

    //init recyle view adapter
    private FoodListAdapter foodListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecipes);
        //init database
        dataBase = AppDataBase.getInstance(this);

        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        addRecepies =findViewById(R.id.addrecipes);
        recyclerView = findViewById(R.id.recyle);
        //set selected item
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        //init recyle view adapter
        foodListAdapter = new FoodListAdapter(this);
        recyclerView.setAdapter(foodListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodListAdapter.setNotes(showFood());



        //add recepies action button
        addRecepies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddRecepies.class));
                overridePendingTransition(0,0);

            }
        });

        //Bottom Navigation View behaviour
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.bag:
                        startActivity(new Intent(getApplicationContext(),BagActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.discover:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.recipes:

                        return true;

                }
                return false;
            }
        });


    }

    public List<Food> showFood()
    {
        return dataBase.foodDAO().getFoods();
    }
}
