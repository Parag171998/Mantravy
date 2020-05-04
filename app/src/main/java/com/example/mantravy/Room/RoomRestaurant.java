package com.example.mantravy.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomRestaurant {

    @PrimaryKey
    @NonNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(Integer averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public RoomRestaurant(String id, String name, String url, Integer averageCostForTwo, String featuredImage) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.averageCostForTwo = averageCostForTwo;
        this.featuredImage = featuredImage;
    }

    @ColumnInfo
    private String name;
    @ColumnInfo
    private String url;
    @ColumnInfo
    private Integer averageCostForTwo;
    @ColumnInfo
    private String featuredImage;

}
