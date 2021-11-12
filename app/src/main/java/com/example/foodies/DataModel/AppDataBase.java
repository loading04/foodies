package com.example.foodies.DataModel;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class },version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    //var
    private static AppDataBase instance;
    public abstract FoodDAO foodDAO();



   public static AppDataBase getInstance(Context context){
       if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"FoodiesDB").allowMainThreadQueries().build();
       }
       return instance;
   }

}
