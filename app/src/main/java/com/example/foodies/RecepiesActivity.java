package com.example.foodies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies.DataModel.AppDataBase;
import com.example.foodies.DataModel.Food;
import com.example.foodies.DataModel.FoodDAO;
import com.example.foodies.DataModel.FoodListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class RecepiesActivity extends AppCompatActivity implements FoodListAdapter.SelectedFood {

    BottomNavigationView bottomNavigationView ;
    RecyclerView recyclerView;
    Button addRecepies;
    private AppDataBase dataBase;
    Toolbar toolbar;


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
        toolbar = findViewById(R.id.toolbar);
        //set selected item
        bottomNavigationView.setSelectedItemId(R.id.recipes);

        //init toolbar
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        //init recyle view adapter
        foodListAdapter = new FoodListAdapter(this,this, dataBase.foodDAO().getFoods());
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

    @Override
    public void selectedFood(Food food) {

        startActivity(new Intent(RecepiesActivity.this,SelectedFoodActivity.class).putExtra("data",food));


    }


    //creation search view for recipes
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchview);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodListAdapter.getFilter().filter(newText);
                return true;
            }
        });


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.searchview)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
