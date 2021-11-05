package com.example.foodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //set selected item
        bottomNavigationView.setSelectedItemId(R.id.discover);




        ////Bottom Navigation View behaviour
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