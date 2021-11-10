package com.example.foodies.DataModel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FoodDAO {

    @Insert
    public void insertFood(Food f);


    @Query("SELECT * from FoodTable")
    public List<Food>getFoods();


    @Update
    void UpdateFood(Food f);


    @Delete
    void DeleteFood (Food f);
}
