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
    @SerializedName("location")
    private String location;
    @SerializedName("isAvailable")
    private boolean isAvailable;
    @SerializedName("lastFed")
    private Date lastFed;
    @SerializedName("highScore")
    private Integer highScore;

    public PetModel(String id, String petName, String type, String location, boolean isAvailable, Date lastFed, Integer highScore) {
        this.id = id;
        this.petName = petName;
        this.type = type;
        this.location = location;
        this.isAvailable = isAvailable;
        this.lastFed = lastFed;
        this.highScore = highScore;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Date getLastFed() {
        return lastFed;
    }

    public void setLastFed(Date lastFed) {
        this.lastFed = lastFed;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }
}