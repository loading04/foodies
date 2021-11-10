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

public class AddRecepies extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //var
    String type;
    String occasion;
    private AppDataBase dataBase;
    //final int CAMERA_intent = 51;
    static final int REQUEST_IMAGE_CAPTURE = 1;
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
    Button  addrecipes;
    Button takepicture;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipes);

        //binding
        addrecipes = findViewById(R.id.addrecipes);
        mealName = findViewById(R.id.namemeal);
        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        ingredient = findViewById(R.id.ingredient);
        servings = findViewById(R.id.servings);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        spinnerOcassion = findViewById(R.id.spinnerOcassion);
        spinnerType = findViewById(R.id.spinnerType);
        takepicture = findViewById(R.id.addpicture);


        //init database
        dataBase = AppDataBase.getInstance(this);

        //init image
        imageBitmap = null;
        //init occasion spinner
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.occasion, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOcassion.setAdapter(adapter);
        spinnerOcassion.setOnItemSelectedListener(this);


        //init meal type spinner
        ArrayAdapter<CharSequence>adapterType = ArrayAdapter.createFromResource(this,R.array.type, R.layout.color_spinner_layout);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);
        spinnerType.setOnItemSelectedListener(this);


        //set selected item in bottom navigation
        bottomNavigationView.setSelectedItemId(R.id.recipes);


        //action addrecipes button

        addrecipes.setOnClickListener(v -> {

            addResepies();


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
                Toast.makeText(parent.getContext(),occasion,Toast.LENGTH_SHORT).show();

            case R.id.spinnerType:
                 type = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),type,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    //methdos
    public void addResepies()
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
                occasion.isEmpty()||
                type.isEmpty()||
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
            Food food = new Food();
            food.setMealName(mealName.getText().toString());
            food.setOccasion(occasion);
            food.setType(type);
            food.setDescription(description.getText().toString());
            food.setIngredient(ingredient.getText().toString());
            food.setPrice(price.getText().toString());
            food.setTime(time.getText().toString());
            food.setServings(servings.getText().toString());
            food.setImage(DataConverter.convertImage2ByteArray(imageBitmap));





            dataBase.foodDAO().insertFood(food);
            Log.i("all elements",food.toString());
            Toast.makeText(this,"Data added successfully",Toast.LENGTH_LONG);

        }



    }

    public List<Food> showFood()
    {
        return dataBase.foodDAO().getFoods();
    }



}





/*
   //take picture
    public void takePicture(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if camera is ready
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent,CAMERA_intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_intent:
                bmpImage = (Bitmap) data.getExtras().get("data");
                if (bmpImage != null)
                {
                    Toast.makeText(this,"picture done",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
*/
