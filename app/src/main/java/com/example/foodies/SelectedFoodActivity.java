package com.example.foodies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodies.DataModel.AppDataBase;
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
    TextView SEdescription;
    ImageView imageView;
    private AppDataBase dataBase;
    Button update;
    Button delete;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_food);


        //init database
        dataBase = AppDataBase.getInstance(this);
        //init
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        SEmeal = findViewById(R.id.mealSE);
        SEoccasion = findViewById(R.id.ocassionselected);
        SEtype = findViewById(R.id.typeselected);
        SEtime= findViewById(R.id.timeselected);
        SEprice = findViewById(R.id.selectedprice);
        SEingredient = findViewById(R.id.ingredientselected);
        SEdescription = findViewById(R.id.descriptionselected);
        imageView = findViewById(R.id.imagemeal);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);


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
            SEdescription.setText(food.getDescription());
            SEingredient.setText(food.getIngredient());
            Bitmap bitmap = DataConverter.convertByteArray2Bitmap(food.getImage());
            imageView.setImageBitmap(bitmap);
            Log.e("check",food.toString());


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SelectedFoodActivity.this,UpdateRecipe.class).putExtra("data",food));

                }

            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog alertDialog = new AlertDialog.Builder(SelectedFoodActivity.this).create();
                    alertDialog.setTitle("Alert Dialog");
                    alertDialog.setMessage("Are you sure to delete this ?");
                    alertDialog.setIcon(R.drawable.ic_bag);

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dataBase.foodDAO().DeleteFood(food);
                            startActivity(new Intent(getApplicationContext(),RecepiesActivity.class));
                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            return;
                        }
                    });

                    alertDialog.show();

                }
            });

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