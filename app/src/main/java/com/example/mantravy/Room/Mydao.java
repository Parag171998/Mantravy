package com.example.mantravy.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Mydao {

    @Insert
    public void addFavFood(RoomRestaurant roomResult);

    @Query("select * from RoomRestaurant")
    public List<RoomRestaurant> getFavFoods();

    @Query("DELETE FROM RoomRestaurant")
    public void deleteAll();

    @Query("select * from RoomRestaurant where id = :id")
    public RoomRestaurant chekIfPresent(String id);

    @Delete
    public void deleteFood(RoomRestaurant roomResult);
}
