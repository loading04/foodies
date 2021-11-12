package com.example.foodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodies.DataModel.DataConverter;
import com.example.foodies.DataModel.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectedFoodActivity extends AppCompatActivity {


    //widgets
    BottomNavigationView bottomNavigationView ;
    TextView SEmeal;
    TextView SEoccasion;
    TextView SEtype;
    TextView SEtime;
    TextView SEprice;
    TextView SEingredient;
    ImageView imageView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_food);

        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        SEmeal = findViewById(R.id.mealSE);
        SEoccasion = findViewById(R.id.ocassionselected);
        SEtype = findViewById(R.id.typeselected);
        SEtime= findViewById(R.id.timeselected);
        SEprice = findViewById(R.id.selectedprice);
        SEingredient = findViewById(R.id.ingredientselected);
        imageView = findViewById(R.id.imagemeal);


        //intent
        Intent intent = getIntent();

        //get data from intent and fill the widgets with selected item in recycle view
        if (intent.getExtras() != null)
        {
            Food food = (Food) intent.getSerializableExtra("data");
            SEmeal.setText(food.getMealName());
            SEoccasion.setText(food.getOccasion());
            SEtype.setText(food.getType());
            SEtime.setText(food.getTime());
            SEprice.setText(food.getPrice());
            SEingredient.setText(food.getIngredient());
            Bitmap bitmap = DataConverter.convertByteArray2Bitmap(food.getImage());
            imageView.setImageBitmap(bitmap);
            Log.e("check",food.toString());

        }


        //bottom nav selection
        bottomNavigationView.setSelectedItemId(R.id.recipes);

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
}