package com.example.mantravy.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {RoomRestaurant.class},version = 1)
public abstract class MyappDatabse extends RoomDatabase {

    public abstract Mydao mydao();

}
