package com.example.foodies;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodies.DataModel.AppDataBase;
import com.example.foodies.DataModel.DataConverter;
import com.example.foodies.DataModel.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class UpdateRecipe extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //var
    String type;
    String occasion;
    private AppDataBase dataBase;

    static final int REQUEST_IMAGE_CAPTURE = 2;

    Bitmap imageBitmap;

    //widgets
    EditText mealName;
    EditText time;
    EditText price;
    EditText servings;
    EditText description;
    EditText ingredient;
    BottomNavigationView bottomNavigationView ;
    Spinner spinnerOcassion;
    Spinner spinnerType;
    Button addrecipes;
    Button takepicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updaterecipe);




        //binding
        addrecipes = findViewById(R.id.addrecipes2);
        mealName = findViewById(R.id.namemeal2);
        time = findViewById(R.id.time2);
        price = findViewById(R.id.price2);
        description = findViewById(R.id.description2);
        ingredient = findViewById(R.id.ingredient2);
        servings = findViewById(R.id.servings2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        spinnerOcassion = findViewById(R.id.spinnerOcassion2);
        spinnerType = findViewById(R.id.spinnerType2);
        takepicture = findViewById(R.id.addpicture2);



        //init database
        dataBase = AppDataBase.getInstance(this);


        //init occasion spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.occasion, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOcassion.setAdapter(adapter);
        spinnerOcassion.setOnItemSelectedListener(this);


        //init meal type spinner
        ArrayAdapter<CharSequence>adapterType = ArrayAdapter.createFromResource(this,R.array.type, R.layout.color_spinner_layout);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);
        spinnerType.setOnItemSelectedListener(this);


        //intent
        Intent intent = getIntent();
        Food food = new Food();


        if (intent.getExtras() != null) {
             food = (Food) intent.getSerializableExtra("data");

            mealName.setText(food.getMealName());
            time.setText(food.getTime());
            price.setText(food.getPrice());
            ingredient.setText(food.getIngredient());
            servings.setText(food.getServings());
            description.setText(food.getDescription());


        }


        Food finalFood = food;
        addrecipes.setOnClickListener(v -> {

            addResepies(finalFood);


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

        takepicture.setOnClickListener(v -> {
            dispatchTakePictureIntent();
        });

    }

    //action take picture
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }


    //the result of take picture intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap == null)
            {
                Toast.makeText(this,"picture didnt taken",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "picture taken", Toast.LENGTH_LONG).show();

            }

        }
        else {
            Toast.makeText(this,"there is a problem with the camera",Toast.LENGTH_LONG).show();
        }
    }





    //spinner occasion/type behaviour
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId())
        {
            case R.id.spinnerOcassion:
                occasion = parent.getItemAtPosition(position).toString();

                break;

            case R.id.spinnerType:
                type = parent.getItemAtPosition(position).toString();

                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //methdos
    public void addResepies(Food foody)
    {
        Log.e("show element",
                "\n mealname :"+mealName.getText().toString()+
                        "\n occasion :"+occasion+
                        "\n type :"+type+
                        "\n description :"+description.getText().toString()+
                        "\n ingredient :"+ingredient.getText().toString()+
                        "\n price :"+price.getText().toString()+
                        "\n time : "+time.getText().toString()+
                        "\n servings : "+time.getText().toString()+
                        "\n bmp :"+imageBitmap

        );

        if(mealName.getText().toString().isEmpty()||

                description.getText().toString().isEmpty()||
                ingredient.getText().toString().isEmpty()||
                price.getText().toString().isEmpty()||
                time.getText().toString().isEmpty()||
                servings.getText().toString().isEmpty()||
                imageBitmap == null)
        {

            Toast.makeText(this,
                    "user Data is missing",
                    Toast.LENGTH_LONG
            ).show();
        }
        else
        {

            foody.setMealName(mealName.getText().toString());
            foody.setDescription(description.getText().toString());
            foody.setIngredient(ingredient.getText().toString());
            foody.setPrice(price.getText().toString());
            foody.setTime(time.getText().toString());
            foody.setServings(servings.getText().toString());
            foody.setImage(DataConverter.convertImage2ByteArray(imageBitmap));




            dataBase.foodDAO().UpdateFood(foody);
            Log.i("all elements",foody.toString());
            Toast.makeText(this,"Data added successfully",Toast.LENGTH_SHORT);

            startActivity(new Intent(getApplicationContext(),RecepiesActivity.class));

        }



    }

    public List<Food> showFood()
    {
        return dataBase.foodDAO().getFoods();
    }
}
