package com.example.foodies.DataModel;

import android.icu.text.SimpleDateFormat;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Arrays;


@Entity(tableName = "FoodTable")
public class Food {



    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "meal_name")
    private String mealName;
    @ColumnInfo(name = "occasion")
    private String occasion;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "ingredient")
    private String ingredient;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "servings")
    private String servings;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte[] image;








    public Food(String mealName, String occasion, String type, String description, String ingredient, String price, String time, String servings) {

        this.mealName = mealName;
        this.occasion = occasion;
        this.type = type;
        this.description = description;
        this.ingredient = ingredient;
        this.price = price;
        this.time = time;
        this.servings = servings;



    }
    public Food() {
    }



    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", mealName='" + mealName + '\'' +
                ", occasion='" + occasion + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", price='" + price + '\'' +
                ", time='" + time + '\'' +
                ", servings='" + servings + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
