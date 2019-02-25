package com.example.feedr;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PetModel {
    @SerializedName("id")
    private String id;
    @SerializedName("petName")
    private String petName;
    @SerializedName("type")
    private String type;
    @SerializedName("isAvailable")
    private boolean isAvailable;
    @SerializedName("lastFed")
    private String lastFed;
    @SerializedName("highScore")
    private Integer highScore;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;


    public PetModel(String id, String petName, String type, boolean isAvailable, String lastFed, Integer highScore, Double latitude, Double longitude) {
        this.id = id;
        this.petName = petName;
        this.type = type;
        this.isAvailable = isAvailable;
        this.lastFed = lastFed;
        this.highScore = highScore;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getLastFed() {
        return lastFed;
    }

    public void setLastFed(String lastFed) {
        this.lastFed = lastFed;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public Double getLatitude(){
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}